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
package com.cioccarellia.kurl.ktx.okhttp

import com.cioccarellia.kurl.api.Api
import com.cioccarellia.kurl.api.Endpoint
import com.cioccarellia.kurl.api.KurlApiContainer
import com.cioccarellia.kurl.dsl.KurlRequestBuilder
import com.cioccarellia.kurl.dsl.KurlScope
import com.cioccarellia.kurl.emptyEndpoint
import com.cioccarellia.kurl.ktx.okhttp.extensions.toOkHttpHeaders
import okhttp3.Request

/**
 * Kurl extension function modifying and yielding an [OkHttp Request Builder][Request.Builder].
 * The passed lambda is applied to the Kurl construction [scope][KurlScope],
 * along with the supplied parameters, and the result can then be converted into an [OkHttp Request][Request].
 *
 * @param       api         The actual base web API endpoint to use
 *                          as root of the request
 * @param       endpoint    The [endpoint][Endpoint] the request is routed to.
 * @param       block       Kurl DSL scope construction lambda
 * */
fun Request.Builder.kurl(
    api: Api,
    endpoint: Endpoint = emptyEndpoint(),
    block: KurlScope.() -> Unit = {}
): Request.Builder {
    val request = com.cioccarellia.kurl.kurl(api, endpoint, block)

    request.let {
        url(it.get())
        headers(it.headers.toOkHttpHeaders())
    }

    return this
}

/**
 * Kurl extension function modifying and yielding an [OkHttp Request Builder][Request.Builder].
 * The passed lambda is applied to the Kurl construction [scope][KurlScope],
 * along with the supplied parameters, and the result can be converted into an [OkHttp Request][Request].
 *
 * @param       container   The actual base web API [container][KurlApiContainer] to use
 *                          as root of the request
 * @param       endpoint    The [endpoint][Endpoint] the request is routed to.
 * @param       block       Kurl DSL scope construction lambda
 * */
fun Request.Builder.kurl(
    container: KurlApiContainer,
    endpoint: Endpoint = emptyEndpoint(),
    block: KurlScope.() -> Unit = {}
): Request.Builder = kurl(container.api, endpoint, block)

/**
 * Kurl extension function modifying and yielding an [OkHttp Request Builder][Request.Builder].
 * The passed lambda is applied to the Kurl construction [scope][KurlScope],
 * along with the supplied parameters, and the result can be converted into an [OkHttp Request][Request].
 *
 * @param       directUrl   The URL where the request is headed at.
 *                          This sticks together the base web API and
 *                          the [endpoint][Endpoint] address
 * @param       block       Kurl DSL scope construction lambda
 * */
fun Request.Builder.kurl(
    directUrl: String,
    block: KurlScope.() -> Unit
): Request.Builder = kurl(Api.direct(directUrl), emptyEndpoint(), block)

/**
 * Kurl extension function building a processed [OkHttp Request][Request] from an [OkHttp Builder][Request.Builder].
 * The passed lambda is applied to the Kurl construction [scope][KurlScope],
 * along with the supplied parameters, and the result is built into an OkHttp Request.
 *
 * @param       kurlBuilder The source Kurl builder
 * */
fun Request.Builder.kurl(
    kurlBuilder: KurlRequestBuilder
): Request = kurl(kurlBuilder.api, kurlBuilder.endpoint).build()