/**
 * Designed and developed by Andrea Cioccarelli (@cioccarellia)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/

package com.cioccarellia.kurl.extensions

internal fun String?.toStringOrEmpty() = this ?: ""

/**
 * Removal
 * */
internal fun String.removeIfEndingWith(suffix: String) = if (endsWith(suffix)) removeSuffix(suffix) else this
internal fun String.removeIfStartingWith(prefix: String) = if (startsWith(prefix)) removePrefix(prefix) else this

internal fun String.removeIfNotEndingWith(suffix: String) = if (!endsWith(suffix)) removeSuffix(suffix) else this
internal fun String.removeIfNotStartingWith(prefix: String) = if (!startsWith(prefix)) removePrefix(prefix) else this

/**
 * Prefixing
 * */
internal fun String.prefixIfEndingWith(prefix: String) = if (endsWith(prefix)) prefix + this else this
internal fun String.prefixIfStartingWith(prefix: String) = if (startsWith(prefix)) prefix + this else this

internal fun String.prefixIfNotEndingWith(prefix: String) = if (!endsWith(prefix)) prefix + this else this
internal fun String.prefixIfNotStartingWith(prefix: String) = if (!startsWith(prefix)) prefix + this else this

/**
 * Suffixing
 * */
internal fun String.suffixIfEndingWith(suffix: String) = if (endsWith(suffix)) this + suffix else this
internal fun String.suffixIfStartingWith(suffix: String) = if (startsWith(suffix)) this + suffix else this

internal fun String.suffixIfNotEndingWith(suffix: String) = if (!endsWith(suffix)) this + suffix else this
internal fun String.suffixIfNotStartingWith(suffix: String) = if (!startsWith(suffix)) this + suffix else this

/**
 * Remove Prefix and Suffix
 * */
internal fun String.removePrefixAndSuffix(surrounding: String) = removePrefix(surrounding).removeSuffix(surrounding)