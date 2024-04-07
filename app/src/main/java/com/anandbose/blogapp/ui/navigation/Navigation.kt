package com.anandbose.blogapp.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.anandbose.blogapp.ui.home.HomeScreen

@Composable
fun Navigator(
    onMenuClicked: () -> Unit,
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier,
) {
    val scope = rememberCoroutineScope()
    val controller = rememberNavController()
    NavHost(
        modifier = modifier.fillMaxSize(),
        navController = controller,
        startDestination = "/home"
    ) {
        composable("/home") {
            HomeScreen(
                windowSizeClass = windowSizeClass,
                onMenuClicked = onMenuClicked,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}