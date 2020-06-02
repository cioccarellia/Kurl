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
package com.cioccarellia.kurlsample

import com.cioccarellia.kurl.api.Api
import com.cioccarellia.kurl.kurl
import com.cioccarellia.kurlsample.containers.GithubApiContainer
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.runBlocking

private val client = HttpClient()

private fun fetchAndPrintURL(url: String) = runBlocking {
    println(client.get<String>(url))
}

fun main() {
    fetchUsingNamedAndExtensionApi()
    fetchUsingInline()
    fetchUsingContainers()
}

fun fetchUsingNamedAndExtensionApi() {
    val api = Api(
        domain = "api.github.com"
    )

    val username = "AndreaCioccarelli"

    fetchAndPrintURL(
        api.kurl {
            endpoint("users/$username/repos")
        }.url()
    )
}

fun fetchUsingInline() = runBlocking {
    val url = kurl("https://api.github.com") {
        endpoint("users/AndreaCioccarelli")
    }.url()

    println(url)

    val result = client.get<String>(
        url
    )

    println(result)
}

fun fetchUsingContainers() {
    /**
     * Composes a request using a custom Kurl Container,
     * fetches a user info and repo pages and prints out
     * the API response
     * */
    val username = "AndreaCioccarelli"
    val container = GithubApiContainer(username)

    fetchAndPrintURL(
        container
            .repoEndpoint(page = 1)
            .url()
    )
}