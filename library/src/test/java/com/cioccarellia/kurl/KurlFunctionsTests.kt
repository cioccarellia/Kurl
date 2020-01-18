/**
 * Designed and developed by Andrea Cioccarelli (@cioccarellia)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cioccarellia.kurl

import com.cioccarellia.kurl.api.Api
import com.google.common.truth.Truth.assertThat
import org.junit.Test

internal class KurlFunctionsTests {

    private val baseApi = Api(
        domain = "heaven.lucifer",
        path = "/v2/elevator/",
        protocol = "htp"
    )

    @Test
    internal fun runKurlWithApi() {
        val username = "AndreaCioccarelli"

        val userRequest = kurl(baseApi) {
            endpoint("users/$username")

            parameters(
                "id" to "23985725872",
                "operation" to 4
            )
        }

        assertThat(
            userRequest.get()
        ).isEqualTo("htp://heaven.lucifer/v2/elevator/users/AndreaCioccarelli?id=23985725872&operation=4")

    }

    @Test
    internal fun runDirectKurl() {
        val url = kurl("localhost/www") {
            endpoint("index.php")

            parameters("id" to "0")
        }.get()

        assertThat(
            url
        ).isEqualTo("localhost/www/index.php?id=0")
    }


    @Test
    internal fun runDirectKurlLocalhost() {
        val url = kurl("localhost") {}.get()

        assertThat(
            url
        ).isEqualTo("localhost")
    }

    @Test
    internal fun runExtensionKurl() {
        val url = baseApi.kurl {
            endpoint("index.php")

            parameters("id" to 0)
        }.get()

        assertThat(
            url
        ).isEqualTo("htp://heaven.lucifer/v2/elevator/index.php?id=0")
    }
}
