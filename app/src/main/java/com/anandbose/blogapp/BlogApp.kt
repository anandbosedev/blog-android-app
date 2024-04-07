package com.anandbose.blogapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.anandbose.blogapp.ui.navigation.Navigator
import com.anandbose.blogapp.ui.sidebar.SidebarVertical
import com.anandbose.blogapp.ui.theme.AnandsBlogTheme
import kotlinx.coroutines.launch

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
            val scope = rememberCoroutineScope()
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
                Navigator(
                    modifier = Modifier.fillMaxSize(),
                    windowSizeClass = windowSizeClass,
                    onMenuClicked = {
                        scope.launch {
                            drawerState.open()
                        }
                    }
                )
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
                Navigator(
                    modifier = Modifier.fillMaxSize(),
                    windowSizeClass = windowSizeClass,
                    onMenuClicked = {}
                )
            }
        }
    }
}