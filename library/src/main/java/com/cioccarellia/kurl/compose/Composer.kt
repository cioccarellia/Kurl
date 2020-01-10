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
package com.cioccarellia.kurl.compose

import com.cioccarellia.kurl.extensions.removePrefixAndSuffix

object Composer {
    fun compose(p1: String, p2: String): String {
        return p1.removePrefixAndSuffix("/") +
            "/" + p2.removePrefixAndSuffix("/")
    }

    fun compose(url: String, endpoint: KurlComposable): String {
        return compose(url, endpoint.kurl())
    }

    fun compose(endpoint: KurlComposable, url: String): String {
        return compose(endpoint.kurl(), url)
    }

    fun compose(p1: KurlComposable, p2: KurlComposable) =
        compose(p1.kurl(), p2.kurl())

    fun sanitize(path: String) = path.removePrefixAndSuffix("/")
}
