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
package com.cioccarellia.kurl.api

import com.cioccarellia.kurl.compose.*
import com.cioccarellia.kurl.constants.KurlConstants
import com.cioccarellia.kurl.extensions.*
import com.cioccarellia.kurl.extensions.removePrefixAndSuffix
import com.cioccarellia.kurl.extensions.suffixIfNotEndingWith

/**
 * Class representing an API base url.
 *
 * @param protocol Optional parameter specifying the protocol used for communication
 *                  between the server and the client. By default it is set to HTTPS.
 *                  It can be either the form "https://" or "https".
 *
 * @param domain The base domain of the website which hosts the server.
 *                  It has to be in the form "get.dns.cloudflare-dns.io"
 *
 * @param port Optional parameter specifying the port the server is running on.
 *
 * @param path Optional parameter specifying the base path to the web API root,
 *                  which is then appended to reach the root server APIs.
 *
 * */
data class Api(
    val protocol: String? = KurlConstants.defaultProtocol,
    val domain: String,
    val port: Int? = null,
    val path: String? = null
) : KurlComposable {
    private constructor(url: String) : this(
        url.protocol(),
        url.domain(),
        url.port(),
        url.path()
    )

    var url = build(protocol, domain, port, path)

    override fun kurl() = url
    override fun toString() = url

    companion object {
        fun of(url: String) = Api(url)

        private fun build(
            protocol: String?,
            domain: String,
            port: Int?,
            path: String?
        ) = buildString {
            check(domain.isNotBlank()) { "Domain must not be blank" }

            protocol?.let {
                check(protocol.isNotBlank()) { "Can not create URL with a blank protocol. Either set it to null, take the default value or specify one." }
            }

            port?.let {
                check(it >= 0)
            }

            if (!protocol.isNullOrBlank()) {
                append(
                    protocol.toLowerCase().suffixIfNotEndingWith("://")
                )
            }

            append(domain.removePrefixAndSuffix("/"))

            port?.let {
                append(":$it")
            }

            path?.let {
                if (it.isNotBlank()) {
                    append("/")
                    append(
                        it.removePrefixAndSuffix("/")
                    )
                }
            }
        }
    }
}
