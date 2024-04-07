package com.anandbose.blogapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.anandbose.blogapp.ui.widget.ResponsiveActionBar
import com.anandbose.blogapp.ui.widget.ResponsiveActionBarWithMenu

@Composable
fun HomeScreen(
    windowSizeClass: WindowSizeClass,
    onMenuClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(WindowInsets.systemBars.asPaddingValues())
    ) {
        val isMenuVisible = when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact, WindowWidthSizeClass.Medium -> true
            WindowWidthSizeClass.Expanded -> {
                windowSizeClass.heightSizeClass != WindowHeightSizeClass.Expanded
            }
            else -> true
        }
        if (isMenuVisible) {
            ResponsiveActionBarWithMenu(
                modifier = Modifier.fillMaxWidth(),
                onMenuClicked = onMenuClicked,
                title = "Anand's Blog"
            )
        }
        HomeScreenContent(
            modifier = Modifier.weight(1f)
        )
    }
}