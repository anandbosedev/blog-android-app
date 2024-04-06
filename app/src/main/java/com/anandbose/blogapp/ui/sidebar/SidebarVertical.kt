package com.anandbose.blogapp.ui.sidebar

import android.view.WindowInsets.Side
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anandbose.blogapp.R
import com.anandbose.blogapp.ui.theme.AnandsBlogTheme

@Composable
fun SidebarVertical(
    modifier: Modifier = Modifier,
    onLinkClicked: (SidebarLink) -> Unit
) {
    Surface(
        modifier = modifier
            .widthIn(min = 240.dp, max = 320.dp)
            .fillMaxHeight(),
        color = MaterialTheme.colorScheme.background,
    ) {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.systemBars.asPaddingValues())
                .padding(horizontal = 16.dp)
                .verticalScroll(
                    state = scrollState,
                )
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Anand's Blog",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outlineVariant,
                        shape = CircleShape
                    )
                    .padding(vertical = 4.dp, horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.open_external_link),
                    contentDescription = "",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "anandbose.dev",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "LINKS",
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.labelMedium,
            )
            Spacer(modifier = Modifier.height(8.dp))
            SidebarItem(
                icon = painterResource(id = R.drawable.icon_github),
                text = "GitHub",
                onClick = { onLinkClicked(SidebarLink.GitHub) }
            )
            SidebarItem(
                icon = painterResource(id = R.drawable.icon_mastodon),
                text = "Mastodon",
                onClick = { onLinkClicked(SidebarLink.Mastodon) }
            )
            SidebarItem(
                icon = painterResource(id = R.drawable.icon_linkedin),
                text = "LinkedIn",
                onClick = { onLinkClicked(SidebarLink.LinkedIn) }
            )
            SidebarItem(
                icon = painterResource(id = R.drawable.icon_rss),
                text = "Feed",
                onClick = { onLinkClicked(SidebarLink.Feed) }
            )
            Spacer(modifier = Modifier.height(32.dp))
            SidebarItem(
                text = "Terms and Conditions",
                isExternal = true,
                onClick = { onLinkClicked(SidebarLink.TermsAndConditions) }
            )
            SidebarItem(
                text = "Privacy Policy",
                isExternal = true,
                onClick = { onLinkClicked(SidebarLink.PrivacyPolicy) }
            )
        }
    }
}

@Composable
fun SidebarItem(
    modifier: Modifier = Modifier,
    icon: Painter? = null,
    text: String,
    isExternal: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .clickable { onClick?.invoke() }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (icon != null) {
            Image(
                modifier = Modifier.size(24.dp),
                painter = icon,
                contentDescription = text
            )
        }
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge
            ,
            modifier = Modifier
                .padding(start = if (icon != null) 16.dp else 0.dp, end = 16.dp)
                .weight(1f)
        )
        if (isExternal) {
            Image(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = R.drawable.open_external_link),
                contentDescription = text
            )
        }
    }
}

@Composable
@Preview(backgroundColor = 0xFFFFFFFF)
fun SidebarItemPreview() {
    AnandsBlogTheme {
        SidebarItem(icon = painterResource(id = R.drawable.ic_launcher_background), text = "GitHub")
    }
}

@Preview
@Composable
fun SidebarVerticalPreview() {
    AnandsBlogTheme {
        SidebarVertical {

        }
    }
}