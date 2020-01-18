/**
 * Designed and developed by Andrea Cioccarelli (@cioccarellia)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/
package com.cioccarellia.kurl.dsl

import com.cioccarellia.kurl.annotations.KurlLauncher
import com.cioccarellia.kurl.api.Api
import com.cioccarellia.kurl.api.Endpoint
import com.cioccarellia.kurl.api.KurlApiContainer
import com.cioccarellia.kurl.emptyEndpoint

/**
 * Represents the Execution Scope of a [Kurl Function][KurlLauncher].
 * */
data class KurlScope @PublishedApi internal constructor(
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

    @PublishedApi internal fun get() = KurlRequestBuilder(api, endpoint, urlParameters, headers, fragment)

    /**
     * Safely adds the passed path to the current [endpoint][Endpoint]
     * */
    fun endpoint(
        path: String
    ): KurlScope = apply {
        endpoint += path
    }

    /**
     * Safely adds the passed [endpoint][Endpoint] to the current one
     * */
    fun endpoint(
        path: Endpoint
    ): KurlScope = apply {
        endpoint += path
    }

    /**
     * Creates a key-value parameter chain to be postfixed to the URL.
     *
     * @param       pairs       Vararg representing the pairs that will be converted into
     *                          parameters in the final URL.
     *                          Equal items do not replace previous ones, but instead they are appended.
     * @param       prefix      String going before the KV chain. By default, "?"
     * @param       separator   String to be used to split the different pairs of KV. By default, "&"
     * @param       suffix      String going after the KV chain. By default, ""
     *
     * */
    fun parameters(
        vararg pairs: Pair<String, Any>,
        prefix: String = "?",
        separator: String = "&",
        suffix: String = ""
    ): KurlScope = parameters(pairs.toMap(), prefix, separator, suffix)


    /**
     * Creates a key-value parameter chain to be postfixed to the URL.
     *
     * @param       parameters  Map representing the pairs that will be converted into
     *                          parameters in the final URL.
     *                          Equal items do not replace previous ones, but instead they are appended.
     * @param       prefix      String going before the KV chain. By default, "?"
     * @param       separator   String to be used to split the different pairs of KV. By default, "&"
     * @param       suffix      String going after the KV chain. By default, ""
     *
     * */
    fun parameters(
        parameters: Map<String, Any>,
        prefix: String = "?",
        separator: String = "&",
        suffix: String = ""
    ): KurlScope = apply {
        parameters(
            UrlParameters(
                parameters,
                prefix,
                separator,
                suffix
            )
        )
    }

    /**
     * Creates a key-value parameter chain to be postfixed to the URL.
     * */
    fun parameters(
        parameters: UrlParameters
    ): KurlScope = apply {
        urlParameters += parameters
    }

    /**
     * Sets the passed [key] to [value] in the header map
     * */
    fun header(
        key: String,
        value: Any
    ): KurlScope = apply {
        headers[key] = value
    }

    /**
     * Decomposes and sets the passed pair into key to value in the header map
     * */
    fun header(
        pair: Pair<String, Any>
    ): KurlScope = apply {
        headers[pair.first] = pair.second
    }

    /**
     * Sets the passed keys to values in the header map
     * */
    fun headers(
        headers: Map<String, Any>
    ): KurlScope = apply {
        this.headers += headers
    }

    /**
     * Decomposes and sets the passed pairs into keys to values in the header map
     * */
    fun headers(
        vararg pairs: Pair<String, Any>
    ): KurlScope = apply {
        pairs.forEach {
            headers[it.first] = it.second
        }
    }

    /**
     * Sets the passed keys to values in the header map
     * */
    fun headers(
        list: Collection<Pair<String, Any>>
    ): KurlScope = apply {
        headers += list
    }

    /**
     * Sets the fragment tag at the end of the URL
     * */
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
