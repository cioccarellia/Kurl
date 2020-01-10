package com.cioccarellia.kurl.compose

import com.cioccarellia.kurl.extensions.removePrefixAndSuffix

object Composer {
    fun compose(p1: String, p2: String): String {
        return "${p1.removeSuffix("/")}/${p2.removePrefix("/")}"
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