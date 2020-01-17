package com.cioccarellia.kurl.annotations

/**
 *
 * */
@Target(AnnotationTarget.CLASS, AnnotationTarget.PROPERTY, AnnotationTarget.LOCAL_VARIABLE, AnnotationTarget.FIELD)
annotation class Enforce(
    val regex: String
)