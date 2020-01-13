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
package com.cioccarellia.kurl

import com.cioccarellia.kurl.api.Api
import com.cioccarellia.kurl.api.Endpoint
import com.cioccarellia.kurl.api.KurlApiContainer
import com.cioccarellia.kurl.dsl.KurlBuilder
import com.cioccarellia.kurl.dsl.KurlContext
import com.cioccarellia.kurl.model.emptyEndpoint

fun Api.kurl(
    endpoint: Endpoint = emptyEndpoint(),
    block: KurlContext.() -> Unit
): KurlBuilder = kurl(this, endpoint = endpoint, block = block)

fun Api.kurl(
    directUrl: String,
    block: KurlContext.() -> Unit
): KurlBuilder = kurl(Api.direct(directUrl), emptyEndpoint(), block)


fun KurlApiContainer.kurl(
    endpoint: Endpoint = emptyEndpoint(),
    block: KurlContext.() -> Unit
): KurlBuilder = kurl(api, endpoint = endpoint, block = block)

fun KurlApiContainer.kurl(
    directUrl: String,
    block: KurlContext.() -> Unit
): KurlBuilder = kurl(Api.direct(directUrl), emptyEndpoint(), block)


