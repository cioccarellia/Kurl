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
package com.cioccarellia.kurl.extensions

/**
 * Extracts the protocol string from a direct url, if present.
 *
 * @param       default     The default value that is going to be used if no protocol is specified.
 *                          By default, it is null, since direct URLs are strict.
 * */
fun String.protocol(default: String? = null): String? {
    return when {
        contains("://") -> {
            split("://")[0].toLowerCase()
        }
        else -> default
    }
}

/**
 * Extracts the domain string from a direct url.
 * */
fun String.domain(): String {
    require(isNotBlank()) {
        "Domain must not be blank"
    }

    val url = if (contains("://")) {
        split("://")[1]
    } else this

    val domainAndPort = if (url.contains("/")) {
        url.split("/")[0]
    } else url

    val domain = if (domainAndPort.contains(":")) {
        domainAndPort.split(":")[0]
    } else domainAndPort

    return domain
}

/**
 * Extracts the URL port from a direct url, if present.
 * */
fun String.port(): Int? {
    if (isBlank()) return null

    val url = if (contains("://")) {
        split("://")[1]
    } else this

    if (!url.contains(":")) return null

    val path = url.split(":")[1]
    if (!path.contains("/")) return path.toInt()

    val port = path.split("/")[0]
    return port.toInt()
}

/**
 * Extracts the web API path string from a direct url, if present.
 * */
fun String.path(): String? {
    if (isBlank()) return null

    val url = if (contains("://")) {
        split("://")[1]
    } else this

    if (!url.contains("/")) return null

    val dividerIndex = url.indexOf('/')

    val path = url.substring(dividerIndex + 1, url.length)

    return path
}