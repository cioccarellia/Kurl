package com.cioccarellia.kurl.reflection

import com.cioccarellia.kurl.annotations.Enforce
import com.cioccarellia.kurl.dsl.KurlBuilder

internal fun KurlBuilder.checkIntegrity(): KurlBuilder {
    val enforceData: Enforce = api::class.annotations.find { it is Enforce } as? Enforce ?: return this

    if (!get().matches(enforceData.regex.toRegex())) {
        throw IllegalStateException("Url ${get()} not matching the regex supplied for integrity check")
    } else return this
}