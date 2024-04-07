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

@Composable
fun BlogCard(
    data: BlogEntryData,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(size = 16.dp),
                color = MaterialTheme.colorScheme.primaryContainer,
            )
            .fillMaxWidth(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            data.image?.let { imageUrl ->
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .clip(
                            RoundedCornerShape(
                                topStart = 16.dp,
                                topEnd = 16.dp
                            )
                        ),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = ""
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = data.title,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.headlineSmall,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = data.publishedDate,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodySmall,
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
@Preview(device = "spec:id=reference_phone,shape=Normal,width=411,height=891,unit=dp,dpi=420",
    showSystemUi = true, showBackground = true
)
fun BlogCardPreview() {
    AnandsBlogTheme {
        BlogCard(
            data = BlogEntryData(
                title = "Rethinking the state of localization in Jetpack Compose — A Kotlin-first approach to localization",
                description = "Rethinking the state of localization in Jetpack Compose — A Kotlin-first approach to localization",
                publishedDate = "Wed, 21 Feb 2024 00:00:00 +0000",
                link = "https://anandbose.dev/2024/02/21/rethinking-localization-of-jetpack-compose-apps/",
                image = "https://anandbose.dev/images/posts/2024-02-10-android-ci-setup/pexels-photo-1427541.jpeg",
            )
        )
    }
}

@Composable
@Preview(device = "spec:id=reference_phone,shape=Normal,width=411,height=891,unit=dp,dpi=420",
    showSystemUi = true, showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES
)
fun BlogCardPreviewNight() {
    AnandsBlogTheme {
        BlogCard(
            data = BlogEntryData(
                title = "Rethinking the state of localization in Jetpack Compose — A Kotlin-first approach to localization",
                description = "Rethinking the state of localization in Jetpack Compose — A Kotlin-first approach to localization",
                publishedDate = "Wed, 21 Feb 2024 00:00:00 +0000",
                link = "https://anandbose.dev/2024/02/21/rethinking-localization-of-jetpack-compose-apps/",
                image = "https://anandbose.dev/images/posts/2024-02-10-android-ci-setup/pexels-photo-1427541.jpeg",
            )
        )
    }
}