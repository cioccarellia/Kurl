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
package com.cioccarellia.kurlsample.containers

import com.cioccarellia.kurl.annotations.Enforce
import com.cioccarellia.kurl.api.Api
import com.cioccarellia.kurl.api.KurlApiContainer
import com.cioccarellia.kurl.kurl
import com.cioccarellia.kurlsample.constants.DemoConstants

class GithubApiContainer(
    private val username: String
) : KurlApiContainer() {

    @Enforce(regex = DemoConstants.urlValidationRegexp)
    override val api = Api.direct(
        url = "https://api.github.com",
        persistentHeaders = mapOf(
            "Accept" to "application/vnd.github.v3+json"
        )
    )

    fun userEndpoint() = kurl {
        endpoint("users/$username")
    }

    fun repoEndpoint(page: Int = 1) = kurl {
        endpoint("users/$username/repos")
        parameters(
            "page" to page,
            "per_page" to "100"
        )
    }
}