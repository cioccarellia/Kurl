package com.cioccarellia.kurl.ktx.ktor

import com.cioccarellia.kurl.api.Api
import com.cioccarellia.kurl.api.Endpoint
import com.google.common.truth.Truth.assertThat
import io.ktor.client.request.HttpRequestBuilder
import org.junit.Test

internal class KtorTest {

    @Test
    internal fun injectKtor() {
        val ktorRequest = HttpRequestBuilder()
            .kurl(
                Api(
                    domain = "a.bc",
                    path = "def/x",
                    persistentHeaders = mapOf(
                        "k1" to 1
                    )
                ),
                Endpoint("ghij/k/lmnop")
            )
            .build()

        assertThat(ktorRequest.url.toString())
            .isEqualTo("https://a.bc/def/x/ghij/k/lmnop")

        assertThat(ktorRequest.headers.isEmpty()).isFalse()
    }
}