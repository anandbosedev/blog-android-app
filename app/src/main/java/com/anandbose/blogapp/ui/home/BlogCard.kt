package com.anandbose.blogapp.ui.home

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.anandbose.blogapp.R
import com.anandbose.blogapp.data.BlogEntryData
import com.anandbose.blogapp.ui.theme.AnandsBlogTheme

@Composable
fun BlogCard(
    data: BlogEntryData,
    onClicked: (BlogEntryData) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
            .clickable { onClicked(data) }
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
                AsyncImage(
                    model = imageUrl,
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
                    placeholder = painterResource(id = R.drawable.ic_launcher_background),
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
                text = data.publishedDate
                    .split(" ")
                    .subList(0, 4)
                    .joinToString(separator = " "),
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
            onClicked = {},
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
            onClicked = {},
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