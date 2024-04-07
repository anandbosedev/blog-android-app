package com.anandbose.blogapp.ui.home

import android.view.Window
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anandbose.blogapp.R
import com.anandbose.blogapp.ui.theme.AnandsBlogTheme

@Composable
fun HomeActionBar(
    windowSizeClass: WindowSizeClass,
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
            HomeActionBarWithMenu(
                onMenuClicked = onMenuClicked,
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            HomeActionBarWithoutMenu(modifier = Modifier.fillMaxWidth())
        }
        HorizontalDivider()
    }
}

@Composable
fun HomeActionBarWithMenu(
    onMenuClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
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
            contentDescription = "Menu"
        )
        Text(
            text = "Anand's Blog",
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
        )
    }
}

@Preview
@Composable
fun HomeActionBarWithMenuPreview() {
    AnandsBlogTheme {
        HomeActionBarWithMenu(onMenuClicked = { /*TODO*/ })
    }
}

@Composable
fun HomeActionBarWithoutMenu(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(color = MaterialTheme.colorScheme.background),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "Anand's Blog",
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
        )
    }
}

@Composable
@Preview
fun HomeActionBarWithoutMenuPreview() {
    AnandsBlogTheme {
        HomeActionBarWithoutMenu()
    }
}