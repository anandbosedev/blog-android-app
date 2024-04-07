package com.anandbose.blogapp.ui.home

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.anandbose.blogapp.R
import com.anandbose.blogapp.data.BlogEntryData
import com.anandbose.blogapp.data.RequestState
import com.anandbose.blogapp.ui.theme.AnandsBlogTheme
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
                    BlogCard(data = it)
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