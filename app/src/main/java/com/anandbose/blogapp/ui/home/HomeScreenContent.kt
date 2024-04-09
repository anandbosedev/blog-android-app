package com.anandbose.blogapp.ui.home

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.anandbose.blogapp.data.RequestState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(),
) {
    Box(modifier = modifier.fillMaxSize()) {
        val state by viewModel.stateFlow.collectAsStateWithLifecycle()
        val pullRefreshState = rememberPullRefreshState(
            refreshing = state.state == RequestState.Loading,
            onRefresh = { viewModel.fetch() }
        )

        state.list?.let { entries ->
            val gridState = rememberLazyStaggeredGridState()
            val context = LocalContext.current
            LazyVerticalStaggeredGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .pullRefresh(state = pullRefreshState),
                state = gridState,
                columns = StaggeredGridCells.Adaptive(minSize = 320.dp),
                verticalItemSpacing = 16.dp,
                horizontalArrangement = Arrangement.spacedBy(space = 16.dp),
                contentPadding = PaddingValues(all = 16.dp)
            ) {
                items(
                    items = entries
                ) {
                    BlogCard(
                        data = it,
                        onClicked = {
                            try {
                                val intent = Intent(Intent.ACTION_VIEW)
                                intent.data = Uri.parse(it.link)
                                context.startActivity(
                                    Intent(
                                        Intent.createChooser(intent, "")
                                    )
                                )
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                    )
                }
            }
        }
        when (state.state) {
            RequestState.Idle -> {}
            RequestState.Loading -> {}
            RequestState.Success -> {}
            RequestState.Error -> {
                ErrorComponent(
                    modifier = Modifier.align(Alignment.Center),
                    title = "Error",
                    description = "Unable to reach the servers right now.",
                    onRetryCallback = {
                        viewModel.fetch()
                    }
                )
            }
        }
        PullRefreshIndicator(
            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.align(Alignment.TopCenter),
            refreshing = state.state == RequestState.Loading,
            state = pullRefreshState,
            scale = true,
        )
    }
}