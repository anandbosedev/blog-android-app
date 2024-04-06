package com.anandbose.blogapp.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DismissibleNavigationDrawer
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(),
) {
    val scope = rememberCoroutineScope()
    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact,
        WindowWidthSizeClass.Medium -> {
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            ModalNavigationDrawer(
                modifier = modifier.fillMaxSize(),
                drawerState = drawerState,
                drawerContent = {
                    SideMenu()
                }
            ) {
                HomeScreenContent(
                    viewModel = viewModel,
                    windowSizeClass = windowSizeClass,
                    onOpenDrawer = {
                       scope.launch {
                           drawerState.open()
                       }
                    }
                )
            }
        }
        WindowWidthSizeClass.Expanded -> {
            PermanentNavigationDrawer(
                modifier = modifier.fillMaxSize(),
                drawerContent = {
                    SideMenu()
                }
            ) {
                HomeScreenContent(
                    viewModel = viewModel,
                    windowSizeClass = windowSizeClass
                )
            }
        }
    }
}