package com.anandbose.blogapp.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anandbose.blogapp.ui.theme.AnandsBlogTheme

@Composable
fun ErrorComponent(
    modifier: Modifier = Modifier,
    onRetryCallback: (() -> Unit)? = null,
    title: String,
    description: String,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier.size(48.dp),
            painter = rememberVectorPainter(Icons.Default.Warning),
            contentDescription = "",
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.error)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )
        if (onRetryCallback != null) {
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onRetryCallback) {
                Text(text = "Retry")
            }
        }
    }
}

@Preview(backgroundColor = 0xFFFFFFFFL, showBackground = true)
@Composable
fun ErrorComponentPreview() {
    AnandsBlogTheme {
        ErrorComponent(
            title = "Error",
            description = "Unable to reach the server for contents.",
            onRetryCallback = {}
        )
    }
}