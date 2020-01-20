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
package com.cioccarellia.kurl

import com.cioccarellia.kurl.annotations.KurlLauncher
import com.cioccarellia.kurl.api.Api
import com.cioccarellia.kurl.api.Endpoint
import com.cioccarellia.kurl.dsl.KurlRequestBuilder
import com.cioccarellia.kurl.dsl.KurlScope

/**
 * Kurl launch function producing a [builder][KurlRequestBuilder], to use for requests.
 * The passed lambda is applied to the Kurl construction [scope][KurlScope],
 * along with the supplied parameters, and the result is then returned.
 *
 * @param       api         The actual base web API endpoint to use
 *                          as root of the request
 * @param       endpoint    The [endpoint][Endpoint] the request is routed to.
 * @param       block       Kurl DSL scope construction lambda
 * */
@KurlLauncher(impliesCheck = false)
fun kurl(
    api: Api,
    endpoint: Endpoint = emptyEndpoint(),
    block: KurlScope.() -> Unit = {}
): KurlRequestBuilder = KurlScope(api, endpoint).apply { block() }.get()

/**
 * Kurl launch function producing a [builder][KurlRequestBuilder], to use for requests.
 * The passed lambda is applied to the Kurl construction [scope][KurlScope],
 * along with the supplied parameters, and the result is then returned.
 *
 * @param       directUrl   The URL where the request is headed at.
 *                          This sticks together the base web API and
 *                          the [endpoint][Endpoint] address
 * @param       block       Kurl DSL scope construction lambda
 * */
@KurlLauncher(impliesCheck = false)
fun kurl(
    directUrl: String,
    block: KurlScope.() -> Unit
): KurlRequestBuilder = KurlScope(Api.direct(directUrl), emptyEndpoint()).apply { block() }.get()