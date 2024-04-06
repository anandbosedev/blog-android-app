package com.anandbose.blogapp.repo

import com.anandbose.blogapp.data.BlogEntryData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface BlogRepository {
    suspend fun getBlogEntries(): List<BlogEntryData>
}

class WebBlogRepository(val httpClient: HttpClient): BlogRepository {
    override suspend fun getBlogEntries(): List<BlogEntryData> = withContext(Dispatchers.IO) {
        httpClient.get("https://anandbose.dev/posts.json").body()
    }
}