package com.anandbose.blogapp

import android.content.Context
import android.content.Intent
import android.net.Uri
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
import androidx.compose.ui.platform.LocalContext
import com.anandbose.blogapp.ui.navigation.Navigator
import com.anandbose.blogapp.ui.sidebar.SidebarLink
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
        val context = LocalContext.current
        if (isModalNavDrawer) {
            val scope = rememberCoroutineScope()
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            ModalNavigationDrawer(
                modifier = Modifier.fillMaxSize(),
                drawerState = drawerState,
                drawerContent = {
                    SidebarVertical(
                        onLinkClicked = {
                            onLinkClicked(context, it)
                        }
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
                        onLinkClicked = {
                            onLinkClicked(context, it)
                        }
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

fun onLinkClicked(context: Context, link: SidebarLink) {
    try {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(
            when (link) {
                SidebarLink.Website -> "https://anandbose.dev"
                SidebarLink.GitHub -> "https://github.com/anandbosedev"
                SidebarLink.Mastodon -> "https://mastodon.online/@anandbose"
                SidebarLink.LinkedIn -> "https://linkedin.com/in/anandbosedev"
                SidebarLink.Feed -> "https://anandbose.dev/feed.xml"
                SidebarLink.TermsAndConditions -> "https://anandbose.dev/terms.html"
                SidebarLink.PrivacyPolicy -> "https://anandbose.dev/privacy.html"
            }
        )
        context.startActivity(
            Intent.createChooser(intent, "")
        )
    } catch (e: Exception) {
        e.printStackTrace()
    }
}