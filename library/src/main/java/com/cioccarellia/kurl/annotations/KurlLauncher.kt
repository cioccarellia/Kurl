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
package com.cioccarellia.kurl.annotations

import com.cioccarellia.kurl.dsl.KurlScope

/**
 * Annotates a function capable of instantiating a
 * [Kurl scope][KurlScope].
 *
 * Those methods are defined as launch functions.
 * */
@Target(AnnotationTarget.FUNCTION)
@MustBeDocumented
annotation class KurlLauncher(
    val impliesCheck: Boolean
)