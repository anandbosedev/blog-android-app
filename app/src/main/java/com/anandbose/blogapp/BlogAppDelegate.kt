package com.anandbose.blogapp

import android.app.Application
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class BlogAppDelegate : Application() {

    lateinit var httpClient: HttpClient
        private set

    override fun onCreate() {
        super.onCreate()
        httpClient = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(
                    Json {
                        isLenient = true
                        prettyPrint = true
                    }
                )
            }
        }
    }
}