package com.anandbose.blogapp.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BlogEntryData(
    @SerialName("title") val title: String,
    @SerialName("description") val description: String,
    @SerialName("pubdate") val publishedDate: String,
    @SerialName("link") val link: String,
    @SerialName("image") val image: String?,
)