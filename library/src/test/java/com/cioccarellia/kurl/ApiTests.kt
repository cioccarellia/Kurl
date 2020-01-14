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

class ApiTests {

    @Test fun testPathlessDoain() =
        assertThat(
            Api(
                domain = "config.kernel.org",
                path = ""
            ).toString()
        ).isEqualTo("https://config.kernel.org")

    @Test fun testCompleteUrl() =
        assertThat(
            Api(
                domain = "i.instagram.com",
                path = "/api/v1/",
                port = 80,
                protocol = "http://"
            ).toString()
        ).isEqualTo("http://i.instagram.com:80/api/v1")

    @Test fun testCustomProtocol() =
        assertThat(
            Api(
                domain = "hell.lucifer",
                protocol = "ftp"
            ).toString()
        ).isEqualTo("ftp://hell.lucifer")


    @Test fun testBlankApiDomain() =
        assertThat(
            kotlin.runCatching {
                Api(
                    domain = "  ",
                    protocol = "mk"
                ).toString()
            }.isFailure
        ).isTrue()

    @Test fun testJoinedApiAndPath() =
        assertThat(
            kotlin.runCatching {
                Api(
                    domain = "mk.com/api/v1"
                ).toString()
            }.isFailure
        ).isTrue()

    @Test fun testDomainOnly() =
        assertThat(
            Api(
                domain = "console.firebase.google.com"
            ).toString()
        ).isEqualTo("https://console.firebase.google.com")

    @Test fun testNullProtocol() =
        assertThat(
            Api(
                domain = "localhost",
                protocol = null,
                port = 6554
            ).toString()
        ).isEqualTo("localhost:6554")

    @Test fun testDirect() =
        assertThat(
            Api.direct("https://webstore.hook.com").toString()
        ).isEqualTo("https://webstore.hook.com")

    @Test fun testLocalhost() =
        assertThat(
            Api.direct("localhost").toString()
        ).isEqualTo("localhost")

    @Test fun testDirectHostAndPort() =
        assertThat(
            Api.direct("localhost:445").toString()
        ).isEqualTo("localhost:445")

    @Test fun testDirectProtocolHostAndPort() =
        assertThat(
            Api.direct("http://cioccarellia.it:445").toString()
        ).isEqualTo("http://cioccarellia.it:445")

    @Test fun testDirectFull() =
        assertThat(
            Api.direct("tftp://localhost:445/UAV/deploy/custom").toString()
        ).isEqualTo("tftp://localhost:445/UAV/deploy/custom")

    @Test fun testDirectBackslashed() =
        assertThat(
            Api.direct("localhost/").toString()
        ).isEqualTo("localhost")

    @Test fun testDirectSubdomainAndPath() =
        assertThat(
            Api.direct("api.github.com/users/AndreaCioccarelli").toString()
        ).isEqualTo("api.github.com/users/AndreaCioccarelli")
}
