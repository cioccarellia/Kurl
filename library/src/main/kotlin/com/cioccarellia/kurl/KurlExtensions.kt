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

import com.cioccarellia.kurl.api.Api
import com.cioccarellia.kurl.api.Endpoint
import com.cioccarellia.kurl.api.KurlApiContainer
import com.cioccarellia.kurl.dsl.KurlRequest
import com.cioccarellia.kurl.dsl.KurlScope

/**
 * Kurl extension function producing a [builder][KurlRequest] from an [API][Api].
 * The passed lambda is applied to the Kurl construction [scope][KurlScope],
 * along with the supplied parameters, and the result is then returned.
 *
 * @param       endpoint    The [endpoint][Endpoint] the request is routed to.
 * @param       block       Kurl DSL scope construction lambda.
 * */
fun Api.kurl(
    endpoint: Endpoint = emptyEndpoint(),
    block: KurlScope.() -> Unit = {}
): KurlRequest = kurl(this, endpoint = endpoint, block = block)

/**
 * Kurl extension function producing a [builder][KurlRequest] from an [Api Container][KurlApiContainer].
 * The passed lambda is applied to the Kurl construction [scope][KurlScope],
 * along with the supplied parameters, and the result is then returned.
 *
 * @param       endpoint    The [endpoint][Endpoint] the request is routed to.
 * @param       block       Kurl DSL scope construction lambda.
 * */
fun KurlApiContainer.kurl(
    endpoint: Endpoint = emptyEndpoint(),
    block: KurlScope.() -> Unit = {}
): KurlRequest = kurl(api, endpoint = endpoint, block = block)