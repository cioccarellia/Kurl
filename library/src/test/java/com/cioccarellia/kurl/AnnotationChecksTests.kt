package com.cioccarellia.kurl

import com.cioccarellia.kurl.annotations.Enforce
import com.cioccarellia.kurl.api.Api
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class AnnotationChecksTests {
    @Test fun check() {
        assertThat(
            kotlin.runCatching {
                val api = @Enforce("hjdyjt") Api(
                    domain = "api.github.com"
                )

                val username = "AndreaCioccarelli"

                api.kurl {
                    endpoint("users/$username/repos")
                }
            }.isFailure
        ).isTrue()
    }
}