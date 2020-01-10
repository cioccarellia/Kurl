/**
 * Designed and developed by Aidan Follestad (@afollestad)
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

import com.cioccarellia.kurl.model.BaseApi
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class BaseApiTest {

    @Test fun url1() =
        assertThat(
            BaseApi(
                domain = "config.kernel.org",
                path = ""
            ).toString()
        ).isEqualTo("https://config.kernel.org")

    @Test fun url2() =
        assertThat(
            BaseApi(
                domain = "i.instagram.com",
                path = "/api/v1/",
                port = 80,
                protocol = "http://"
            ).toString()
        ).isEqualTo("http://i.instagram.com:80/api/v1")

    @Test fun url3() =
        assertThat(
            BaseApi(
                domain = "hell.lucifer",
                protocol = "ftp"
            ).toString()
        ).isEqualTo("ftp://hell.lucifer")

    @Test fun url4() =
        assertThat(
            BaseApi(
                domain = "console.firebase.google.com"
            ).toString()
        ).isEqualTo("https://console.firebase.google.com")

    @Test fun url5() =
        assertThat(
            BaseApi(
                domain = "localhost",
                protocol = null,
                port = 6554
            ).toString()
        ).isEqualTo("localhost:6554")

    @Test fun url6() =
        assertThat(
            BaseApi.of("https://webstore.hook.com").toString()
        ).isEqualTo("https://webstore.hook.com")

    @Test fun url7() =
        assertThat(
            BaseApi.of("localhost").toString()
        ).isEqualTo("localhost")

    @Test fun url8() =
        assertThat(
            BaseApi.of("localhost:445").toString()
        ).isEqualTo("localhost:445")


    @Test fun url9() =
        assertThat(
            BaseApi.of("tftp://localhost:445/UAV/deploy/custom").toString()
        ).isEqualTo("tftp://localhost:445/UAV/deploy/custom")

    @Test fun url10() =
        assertThat(
            BaseApi.of("localhost/").toString()
        ).isEqualTo("localhost")
}