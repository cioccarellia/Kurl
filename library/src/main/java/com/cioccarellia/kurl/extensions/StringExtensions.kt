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
 * Suffixes the string with the passed [suffix], is not already terminating with it as its last characters.
 * */
internal fun String.suffixIfNotEndingWith(suffix: String) = if (!endsWith(suffix)) this + suffix else this

/**
 * Un-Prefixes and Suffixes a string if the margin contains the [surrounding].
 * */
internal fun String.removePrefixAndSuffix(surrounding: String) = removePrefix(surrounding).removeSuffix(surrounding)
