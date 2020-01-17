package com.cioccarellia.kurl.ktx.okhttp

import com.cioccarellia.kurl.api.Api
import com.cioccarellia.kurl.api.Endpoint
import com.google.common.truth.Truth.assertThat
import okhttp3.Request
import org.junit.Test

class OkHttpTest {
    private val requestBuilder = Request.Builder()

    @Test
    internal fun finalizedDirectRequestBuilder() {
        val request = requestBuilder
            .kurl("https://api.github.com") {
                endpoint("users/AndreaCioccarelli")
            }
            .build()

        assertThat(
            request.url.toString()
        ).isEqualTo("https://api.github.com/users/AndreaCioccarelli")
    }

    @Test
    internal fun finalizedInlineRequestBuilder() {
        val request = requestBuilder
            .kurl(
                Api(
                    protocol = "https://",
                    domain = "api.github.com"
                ),
                Endpoint("users/AndreaCioccarelli")
            )
            .build()

        assertThat(
            request.url.toString()
        ).isEqualTo("https://api.github.com/users/AndreaCioccarelli")
    }


    @Test
    internal fun finalizedInvalidRequest() {
        val request = requestBuilder
            .kurl(
                Api(
                    domain = "api.github.com",
                    port = 443,
                    path = "status"

                ),
                Endpoint("")
            )
            .build()

        assertThat(
            request.url.toString()
        ).isEqualTo("https://api.github.com/status")
    }
}