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

import com.cioccarellia.kurl.compose.KurlComposable
import com.cioccarellia.kurl.constants.KurlConstants
import com.cioccarellia.kurl.extensions.domain
import com.cioccarellia.kurl.extensions.path
import com.cioccarellia.kurl.extensions.port
import com.cioccarellia.kurl.extensions.protocol
import com.cioccarellia.kurl.extensions.removePrefixAndSuffix
import com.cioccarellia.kurl.extensions.suffixIfNotEndingWith
import com.cioccarellia.kurl.model.emptyHeaders
import java.net.URL

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
    val path: String? = null,
    val persistentHeaders: Map<String, Any> = emptyHeaders()
) : KurlComposable {

    @PublishedApi internal constructor(
        url: String,
        persistentHeaders: Map<String, Any>
    ) : this(
        protocol = url.protocol(),
        domain = url.domain(),
        port = url.port(),
        path = url.path(),
        persistentHeaders = persistentHeaders
    )

    var url = build(protocol, domain, port, path)

    override fun url() = url
    override fun toString() = url

    companion object {
        /**
         * Direct
         * */
        fun direct(
            url: String,
            persistentHeaders: Map<String, Any> = emptyHeaders()
        ) = Api(url, persistentHeaders.toMutableMap())

        fun direct(
            url: URL,
            persistentHeaders: Map<String, Any> = emptyHeaders()
        ) = Api(url.toString(), persistentHeaders.toMutableMap())

        private fun build(
            protocol: String?,
            domain: String,
            port: Int?,
            path: String?
        ) = buildString {
            require(domain.isNotBlank()) {
                "The domain field must not be blank"
            }

            require(!domain.contains("://")) {
                "Must not include protocol inside domain ($domain)"
            }

            require(!domain.contains("/")) {
                "Must not include API web path inside domain, specify it into the 'path' field"
            }

            protocol?.let {
                require(protocol.isNotBlank()) {
                    "Can not silently create a URL with a blank protocol. Either set it to null, take the default value (https) or specify one."
                }
            }

            port?.let { require(it >= 0) }

            protocol?.let {
                append(
                    protocol
                        .toLowerCase()
                        .suffixIfNotEndingWith("://")
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
