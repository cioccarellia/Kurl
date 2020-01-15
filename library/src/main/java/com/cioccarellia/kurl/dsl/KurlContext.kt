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
package com.cioccarellia.kurl.dsl

import com.cioccarellia.kurl.api.Api
import com.cioccarellia.kurl.api.Endpoint
import com.cioccarellia.kurl.api.KurlApiContainer
import com.cioccarellia.kurl.emptyEndpoint

data class KurlContext @PublishedApi internal constructor(
    private val api: Api,
    private val endpoint: Endpoint = emptyEndpoint()
) {
    @PublishedApi internal constructor(
        apiContainer: KurlApiContainer,
        endpoint: Endpoint = emptyEndpoint()
    ) : this(apiContainer.api, endpoint)

    @PublishedApi internal constructor(
        fullEndpoint: String
    ) : this(Api.direct(fullEndpoint))

    @PublishedApi internal val headers: MutableMap<String, Any> = api.persistentHeaders.toMutableMap()
    @PublishedApi internal val urlParameters = UrlParameters()
    @PublishedApi internal var fragment = ""

    @PublishedApi internal fun get() = KurlBuilder(api, endpoint, urlParameters, headers, fragment)

    fun endpoint(
        path: String
    ): KurlContext = apply {
        endpoint += path
    }

    fun endpoint(
        path: Endpoint
    ): KurlContext = apply {
        endpoint += path
    }

    fun parameters(
        vararg pairs: Pair<String, Any>,
        prefix: String = "?",
        separator: String = "&",
        suffix: String = ""
    ): KurlContext = parameters(pairs.toMap(), prefix, separator, suffix)

    fun parameters(
        parameters: Map<String, Any>,
        prefix: String = "?",
        separator: String = "&",
        suffix: String = ""
    ): KurlContext = apply {
        parameters(
            UrlParameters(
                parameters,
                prefix,
                separator,
                suffix
            )
        )
    }

    fun parameters(
        parameters: UrlParameters
    ): KurlContext = apply {
        urlParameters += parameters
    }

    fun header(
        key: String,
        value: Any
    ): KurlContext = apply {
        headers[key] = value
    }

    fun header(
        pair: Pair<String, Any>
    ): KurlContext = apply {
        headers[pair.first] = pair.second
    }

    fun headers(
        vararg pairs: Pair<String, Any>
    ): KurlContext = apply {
        pairs.forEach {
            headers[it.first] = it.second
        }
    }

    fun headers(
        headers: Map<String, Any>
    ): KurlContext = apply {
        this.headers += headers
    }

    fun headers(
        list: Collection<Pair<String, Any>>
    ): KurlContext = apply {
        headers += list
    }

    fun fragment(
        fragment: String
    ) = apply {
        this.fragment = if (fragment.startsWith("#")) {
            fragment
        } else {
            "#$fragment"
        }
    }
}
