package com.cioccarellia.kurl.annotations

@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS, AnnotationTarget.PROPERTY, AnnotationTarget.EXPRESSION)
annotation class Enforce(
    val regex: String
)