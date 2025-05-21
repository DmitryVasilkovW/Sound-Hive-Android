package org.sound.hive.android.api

import okhttp3.*
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import kotlin.test.*

class HeadersInterceptorTest {

    @Test
    fun `interceptor should add Accept, Content-Type, and Platform headers`() {
        val interceptor = HeadersInterceptor()

        val originalRequest = Request.Builder()
            .url("https://example.com")
            .build()

        val chain = mock<Interceptor.Chain> {
            on { request() } doReturn originalRequest
        }

        val response = Response.Builder()
            .request(originalRequest)
            .protocol(Protocol.HTTP_1_1)
            .code(200)
            .message("OK")
            .body(ResponseBody.create(null, ""))
            .build()

        whenever(chain.proceed(any())).thenReturn(response)

        interceptor.intercept(chain)

        argumentCaptor<Request>().apply {
            verify(chain).proceed(capture())
            val interceptedRequest = firstValue

            assertEquals("application/json", interceptedRequest.header("Accept"))
            assertEquals("application/json", interceptedRequest.header("Content-Type"))
            assertEquals("Android", interceptedRequest.header("Platform"))
        }
    }
}
