package com.anandbose.blogapp.ui.widget

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anandbose.blogapp.R
import com.anandbose.blogapp.ui.theme.AnandsBlogTheme

@Composable
fun ResponsiveActionBar(
    windowSizeClass: WindowSizeClass,
    title: String,
    onMenuClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val isMenuVisible = when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact, WindowWidthSizeClass.Medium -> true
        WindowWidthSizeClass.Expanded -> {
            windowSizeClass.heightSizeClass != WindowHeightSizeClass.Expanded
        }
        else -> true
    }
    Column(modifier = modifier.fillMaxWidth()) {
        if (isMenuVisible) {
            ResponsiveActionBarWithMenu(
                onMenuClicked = onMenuClicked,
                modifier = Modifier.fillMaxWidth(),
                title = title,
            )
        } else {
            ResponsiveActionBarWithoutMenu(
                modifier = Modifier.fillMaxWidth(),
                title = title,
            )
        }
    }
}

@Composable
fun ResponsiveActionBarWithMenu(
    onMenuClicked: () -> Unit,
    modifier: Modifier = Modifier,
    title: String,
) {
    Column {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(color = MaterialTheme.colorScheme.background),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier
                    .size(48.dp)
                    .clickable { onMenuClicked() }
                    .padding(8.dp),
                painter = painterResource(id = R.drawable.icon_menu),
                contentDescription = "Menu",
                colorFilter = ColorFilter.tint(
                    color = MaterialTheme.colorScheme.onBackground,
                )
            )
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
            )
        }
        HorizontalDivider()
    }
}

@Preview
@Composable
fun ResponsiveActionBarWithMenuPreview() {
    AnandsBlogTheme {
        ResponsiveActionBarWithMenu(title = "Anand's Blog", onMenuClicked = { /*TODO*/ })
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ResponsiveActionBarWithMenuPreviewNight() {
    AnandsBlogTheme {
        ResponsiveActionBarWithMenu(title = "Anand's Blog", onMenuClicked = { /*TODO*/ })
    }
}

@Composable
fun ResponsiveActionBarWithoutMenu(
    modifier: Modifier = Modifier,
    title: String,
) {
    Column {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(color = MaterialTheme.colorScheme.background),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            )
        }
        HorizontalDivider()
    }
}

@Composable
@Preview
fun ResponsiveActionBarWithoutMenuPreview() {
    AnandsBlogTheme {
        ResponsiveActionBarWithoutMenu(title = "Anand's Blog")
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun ResponsiveActionBarWithoutMenuPreviewNight() {
    AnandsBlogTheme {
        ResponsiveActionBarWithoutMenu(title = "Anand's Blog")
    }
}