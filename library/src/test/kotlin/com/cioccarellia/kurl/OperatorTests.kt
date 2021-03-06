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

import com.cioccarellia.kurl.api.Endpoint
import com.google.common.truth.Truth.assertThat
import org.junit.Test

internal class OperatorTests {

    @Test
    internal fun sumEndpoints() {
        val data = Endpoint("data")
        val space = Endpoint("space")

        val sum = data + space

        assertThat(sum.url())
            .isEqualTo("data/space")
    }

    @Test
    internal fun sumUrlAndEndpoint() {
        val space = Endpoint("space")
        val data = "data"

        val sum = space + data

        assertThat(sum)
            .isEqualTo("space/data")
    }
}