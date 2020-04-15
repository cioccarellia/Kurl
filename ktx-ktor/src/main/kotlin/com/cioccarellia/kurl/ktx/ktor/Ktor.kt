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
package com.cioccarellia.kurl.ktx.ktor

import com.cioccarellia.kurl.api.Api
import com.cioccarellia.kurl.api.Endpoint
import com.cioccarellia.kurl.api.KurlApiContainer
import com.cioccarellia.kurl.compose.Composer
import com.cioccarellia.kurl.dsl.KurlRequest
import com.cioccarellia.kurl.dsl.KurlScope
import com.cioccarellia.kurl.emptyEndpoint
import com.cioccarellia.kurl.ktx.ktor.extensions.toKtorProtocol
import com.cioccarellia.kurl.ktx.ktor.extensions.toStringOrEmpty
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.http.DEFAULT_PORT
import io.ktor.http.URLProtocol

/**
 * Kurl extension function modifying and yielding a [Ktor Builder][HttpRequestBuilder].
 * The passed lambda is applied to the Kurl construction [scope][KurlScope],
 * along with the supplied parameters, and the result can then be converted into a [Ktor Request][HttpRequestData].
 *
 * @param       api         The actual base web API endpoint to use
 *                          as root of the request
 * @param       endpoint    The [endpoint][Endpoint] the request is routed to.
 * @param       block       Kurl DSL scope construction lambda
 * */
fun HttpRequestBuilder.kurl(
    api: Api,
    endpoint: Endpoint = emptyEndpoint(),
    block: KurlScope.() -> Unit = {}
): HttpRequestBuilder {
    val kurlRequest = com.cioccarellia.kurl.kurl(api, endpoint, block)

    url {
        protocol = kurlRequest.api.protocol?.toKtorProtocol() ?: URLProtocol.HTTPS
        host = kurlRequest.api.domain
        port = kurlRequest.api.port ?: DEFAULT_PORT

        encodedPath = kotlin.run {
            val apiPath = kurlRequest.api.path.toStringOrEmpty()
            val paramPath = kurlRequest.endpoint

            Composer.compose(apiPath, paramPath)
        }
    }

    headers {
        kurlRequest.headers.forEach {
            header(it.key, it.value)
        }
    }

    kurlRequest.urlParameters.parameters.forEach {
        parameter(it.key, it.value)
    }

    return this
}

/**
 * Kurl extension function modifying and yielding a [Ktor Builder][HttpRequestBuilder].
 * The passed lambda is applied to the Kurl construction [scope][KurlScope],
 * along with the supplied parameters, and the result can then be converted into a [Ktor Request][HttpRequestData].
 *
 * @param       container   The actual base web API [container][KurlApiContainer] to use
 *                          as root of the request
 * @param       endpoint    The [endpoint][Endpoint] the request is routed to.
 * @param       block       Kurl DSL scope construction lambda
 * */
fun HttpRequestBuilder.kurl(
    container: KurlApiContainer,
    endpoint: Endpoint = emptyEndpoint(),
    block: KurlScope.() -> Unit = {}
): HttpRequestBuilder = kurl(container.api, endpoint, block)

/**
 * Kurl extension function modifying and yielding a [Ktor Builder][HttpRequestBuilder].
 * The passed lambda is applied to the Kurl construction [scope][KurlScope],
 * along with the supplied parameters, and the result can then be converted into a [Ktor Request][HttpRequestData].
 *
 * @param       directUrl   The URL where the request is headed at.
 *                          This sticks together the base web API and
 *                          the [endpoint][Endpoint] address
 * @param       block       Kurl DSL scope construction lambda
 * */
fun HttpRequestBuilder.kurl(
    directUrl: String,
    block: KurlScope.() -> Unit = {}
): HttpRequestBuilder = kurl(Api.direct(directUrl), emptyEndpoint(), block)

/**
 * Kurl extension function building a processed [Ktor Request][HttpRequestBuilder] from a [Ktor Builder][HttpRequestData]
 * The passed lambda is applied to the Kurl construction [scope][KurlScope],
 * along with the supplied parameters, and the result is built into a [Ktor Request][HttpRequestData].
 *
 * @param       kurlRequest The source Kurl request
 * */
fun HttpRequestBuilder.kurl(
    kurlRequest: KurlRequest
): HttpRequestData = kurl(kurlRequest.api, kurlRequest.endpoint).build()