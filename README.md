# Kurl - ⚠️ don't use this library until I'm gone :) ⚠️
[![Download](https://api.bintray.com/packages/cioccarellia/kurl/kurl/images/download.svg)](https://bintray.com/cioccarellia/kurl/kurl/_latestVersion)
[![CircleCI](https://circleci.com/gh/AndreaCioccarelli/Kurl/tree/master.svg?style=svg)](https://circleci.com/gh/AndreaCioccarelli/Kurl/tree/master)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/2d207f5ac27b4aed8276803b18c29115)](https://www.codacy.com/manual/cioccarellia/Kurl?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=AndreaCioccarelli/Kurl&amp;utm_campaign=Badge_Grade)
[![codecov](https://codecov.io/gh/AndreaCioccarelli/Kurl/branch/master/graph/badge.svg)](https://codecov.io/gh/AndreaCioccarelli/Kurl)
[![Kotlin](https://img.shields.io/badge/Kotlin-1.3.61-orange.svg?style=flat)](http://kotlinlang.org)
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0.html)

Kurl is a library that implements, structures, modularizes and provides testability for APIs implementations.
It is designed to turn basic strings (and URLs) operations into consistent and idiomatic Kotlin code.

## Installation
```gradle
dependencies {
    implementation 'com.cioccarellia:kurl:$version'
}
```

## Introduction
An API a is _where_ the server is being hosted.
To create software which is capable of accessing a minimally structured API you have to know the API root url and the scheme used by the server.

An URL is composed by several parts. Generally, we can group them into 3 categories:

```html
https://github.com/AndreaCioccarelli/Kurl/
```
 - Protocol: `https`
 - Domain: `github.com`
 - Web Path: `AndreaCioccarelli/Kurl`
