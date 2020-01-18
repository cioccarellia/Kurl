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
package com.cioccarellia.kurl.compose

import com.cioccarellia.kurl.extensions.removePrefixAndSuffix

/**
 * Utility class used to join together URLs in a way
 * that maximizes usability and minimizes error likeliness
 * */
object Composer {
    /**
     * Joins two strings as two URLs
     * */
    fun compose(
        p1: String,
        p2: String
    ): String {
        return when {
            p1.isEmpty() && p2.isEmpty() -> ""
            p1.isEmpty() -> sanitize(p2)
            p2.isEmpty() -> sanitize(p1)
            else -> sanitize(p1) + "/" + sanitize(p2)
        }
    }

    /**
     * Joins string and [composable][KurlComposable] as two URLs
     * */
    fun compose(
        url: String,
        endpoint: KurlComposable
    ): String {
        return compose(url, endpoint.url())
    }

    /**
     * Joins string and [composable][KurlComposable] as two URLs
     * */
    fun compose(
        endpoint: KurlComposable,
        url: String
    ): String {
        return compose(endpoint.url(), url)
    }

    /**
     * Joins two [composables][KurlComposable] as two URLs
     * */
    fun compose(
        p1: KurlComposable,
        p2: KurlComposable
    ) = compose(p1.url(), p2.url())

    /**
     * Makes sure an URL is not malformed
     * */
    fun sanitize(url: String) = url.removePrefixAndSuffix("/")
}