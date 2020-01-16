package com.cioccarellia.kurl.annotations

@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS, AnnotationTarget.PROPERTY, AnnotationTarget.EXPRESSION, AnnotationTarget.LOCAL_VARIABLE,
    AnnotationTarget.FIELD
)
annotation class Enforce(
    val regex: String
)