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
import com.cioccarellia.kurl.model.Method
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class KurlFunctionsTest {

    val baseApi = Api(
        domain = "heaven.lucifer",
        path = "/v1/skywalk/",
        protocol = "ftp"
    )

    @Test
    fun run() {
        val username = "AndreaCioccarelli"

        val userRequest = kurl(baseApi) {
            endpoint("users/$username")
            method(Method.PUT)

            parameters(
                "id" to "23985725872",
                "operation" to 4
            )
        }

        assertThat(
            userRequest.url
        ).isEqualTo("ftp://heaven.lucifer/v1/skywalk/users/AndreaCioccarelli?id=23985725872&operation=4")

        assertThat(
            kurl("localhost/www/PhpStorm") {
                endpoint("index.php")
                method(Method.POST)

                parameters("id" to "0")
            }.url
        ).isEqualTo("localhost/www/PhpStorm/index.php?id=0")
    }
}
