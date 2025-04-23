package org.sound.hive.android.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import okhttp3.Request
import org.sound.hive.android.model.User

class ApiServiceImpl(
    private val client: HttpClient
) : ApiService {


    override suspend fun getUser(userId: String) {
        val request = Request.Builder()
            .url("https://api.example.com/users/$userId")
            .header("Accept", "application/json")
            .build()
    }
}