package org.sound.hive.android.api

import okhttp3.Interceptor
import okhttp3.Response

class HeadersInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .header("Accept", "application/json")
            .header("Content-Type", "application/json")
            .header("Platform", "Android")
            .build()
        return chain.proceed(request)
    }
}