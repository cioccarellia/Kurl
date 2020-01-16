package com.cioccarellia.kurl.reflection

import com.cioccarellia.kurl.annotations.Enforce
import com.cioccarellia.kurl.api.Api
import com.cioccarellia.kurl.dsl.KurlBuilder
import kotlin.reflect.full.findAnnotation

val apis = @Enforce("") Api(
    domain = "api.github.com"
)

internal fun KurlBuilder.checkIntegrity(): KurlBuilder {
    val enforceData: Enforce = api::class.findAnnotation<Enforce>() ?: return this

    if (!get().matches(enforceData.regex.toRegex())) {
        throw IllegalStateException("Url ${get()} not matching the regex supplied for integrity check")
    } else return this
}