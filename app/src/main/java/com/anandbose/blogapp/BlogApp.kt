package com.anandbose.blogapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.anandbose.blogapp.ui.home.HomeScreen
import com.anandbose.blogapp.ui.home.HomeScreenContent
import com.anandbose.blogapp.ui.sidebar.SidebarVertical
import com.anandbose.blogapp.ui.theme.AnandsBlogTheme

@Composable
fun BlogApp(windowSizeClass: WindowSizeClass) {
    AnandsBlogTheme {
        val isModalNavDrawer = when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact, WindowWidthSizeClass.Medium -> true
            WindowWidthSizeClass.Expanded -> {
                windowSizeClass.heightSizeClass != WindowHeightSizeClass.Expanded
            }
            else -> true
        }
        if (isModalNavDrawer) {
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            ModalNavigationDrawer(
                modifier = Modifier.fillMaxSize(),
                drawerState = drawerState,
                drawerContent = {
                    SidebarVertical(
                        onLinkClicked = {}
                    )
                }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Magenta),
                ) {
                    Text(text = "Content", modifier = Modifier.align(Alignment.Center))
                }
            }
        } else {
            PermanentNavigationDrawer(
                modifier = Modifier.fillMaxSize(),
                drawerContent = {
                    SidebarVertical(
                        onLinkClicked = {}
                    )
                }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Magenta),
                ) {
                    Text(text = "Content", modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }
}