package com.anandbose.blogapp

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.anandbose.blogapp.ui.home.HomeScreen
import com.anandbose.blogapp.ui.theme.AnandsBlogTheme

@Composable
fun BlogApp(windowSizeClass: WindowSizeClass) {
    AnandsBlogTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = "/home"
        ) {
            composable(route = "/home") {
                HomeScreen(windowSizeClass = windowSizeClass)
            }
        }
    }
}