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
import com.cioccarellia.kurl.dsl.KurlContext
import com.cioccarellia.kurl.model.emptyEndpoint
import okhttp3.Headers.Companion.toHeaders
import okhttp3.Request

fun Request.Builder.kurl(
    api: Api,
    endpoint: Endpoint = emptyEndpoint(),
    block: KurlContext.() -> Unit = {}
): Request.Builder {
    val request = com.cioccarellia.kurl.kurl(api, endpoint, block)

    request.let {
        url(it.get())
        headers(
            it.headers
            .mapValues {
                it.value.toString()
            }
            .toHeaders()
        )
    }

    return this
}

fun Request.Builder.kurl(
    container: KurlApiContainer,
    endpoint: Endpoint = emptyEndpoint(),
    block: KurlContext.() -> Unit = {}
): Request.Builder = kurl(container.api, endpoint, block)

fun Request.Builder.kurl(
    directUrl: String,
    block: KurlContext.() -> Unit
): Request.Builder = kurl(Api.direct(directUrl), emptyEndpoint(), block)

