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
package com.cioccarellia.kurl.model

data class UrlParameters(
    var parameters: Map<String, Any> = emptyMap(),
    var prefix: String = "?",
    var separator: String = "&",
    var suffix: String = ""
) {
    override fun toString() = when {
        parameters.isEmpty() -> ""
        else -> parameters.toList().joinToString(
            separator = separator,
            prefix = prefix,
            postfix = suffix
        ) {
            "${it.first}=${it.second}"
        }
    }

    operator fun plusAssign(other: UrlParameters) {
        parameters += other.parameters
        prefix = other.prefix
        separator = other.separator
        suffix = other.suffix
    }
}
