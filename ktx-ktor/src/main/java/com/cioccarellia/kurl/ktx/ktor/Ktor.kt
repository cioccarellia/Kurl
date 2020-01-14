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
import com.cioccarellia.kurl.dsl.KurlContext
import com.cioccarellia.kurl.ktx.ktor.extensions.toKtorProtocol
import com.cioccarellia.kurl.ktx.ktor.extensions.toStringOrEmpty
import com.cioccarellia.kurl.model.emptyEndpoint
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.http.DEFAULT_PORT
import io.ktor.http.URLProtocol

fun HttpRequestBuilder.kurl(
    api: Api,
    endpoint: Endpoint = emptyEndpoint(),
    block: KurlContext.() -> Unit = {}
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

fun HttpRequestBuilder.kurl(
    container: KurlApiContainer,
    endpoint: Endpoint = emptyEndpoint(),
    block: KurlContext.() -> Unit = {}
): HttpRequestBuilder = kurl(container.api, endpoint, block)

fun HttpRequestBuilder.kurl(
    directUrl: String,
    block: KurlContext.() -> Unit = {}
): HttpRequestBuilder = kurl(Api.direct(directUrl), emptyEndpoint(), block)