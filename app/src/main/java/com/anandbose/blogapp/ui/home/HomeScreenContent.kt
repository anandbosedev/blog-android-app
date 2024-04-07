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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.anandbose.blogapp.data.RequestState
import com.anandbose.blogapp.ui.widget.ProgressIndicator

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(),
) {
    Box(modifier = modifier.fillMaxSize()) {
        val state by viewModel.stateFlow.collectAsStateWithLifecycle()

        state.list?.let { entries ->
            val gridState = rememberLazyStaggeredGridState()
            val context = LocalContext.current
            LazyVerticalStaggeredGrid(
                modifier = Modifier.fillMaxSize(),
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
            RequestState.Loading -> {
                ProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            RequestState.Success -> {}
            RequestState.Error -> {}
        }
    }
}