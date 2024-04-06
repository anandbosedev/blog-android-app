package com.anandbose.blogapp.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anandbose.blogapp.data.RequestState
import com.anandbose.blogapp.ui.widget.ProgressIndicator

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    onOpenDrawer: (() -> Unit)? = null,
    viewModel: HomeViewModel,
    windowSizeClass: WindowSizeClass,
) {
    Box(modifier = modifier.fillMaxSize()) {
        val state by viewModel.stateFlow.collectAsStateWithLifecycle()
        state.list?.let { 
            val lazyGridState = rememberLazyGridState()
            LazyVerticalGrid(
                state = lazyGridState,
                columns = GridCells.Adaptive(minSize = 240.dp)
            ) {
                items(it) {
                    Column(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(text = it.title)
                        Text(text = it.publishedDate)
                    }
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
            RequestState.Error -> {
                
            }
        }
    }
}