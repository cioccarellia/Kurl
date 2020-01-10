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

import com.cioccarellia.kurl.api.Endpoint
import com.cioccarellia.kurl.compose.Composer
import com.cioccarellia.kurl.model.Method
import com.cioccarellia.kurl.model.UrlParameters

data class KurlRequest(
    @PublishedApi
    internal val apiUrl: String,
    @PublishedApi
    internal val endpoint: Endpoint,
    @PublishedApi
    internal val urlParameters: UrlParameters,
    @PublishedApi
    internal val method: Method,
    @PublishedApi
    internal val headers: Map<String, String>
) {
    val url: String
        get() = Composer.compose(apiUrl, endpoint) + urlParameters.toString()
}
