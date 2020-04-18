# Kurl
[![Download](https://api.bintray.com/packages/cioccarellia/kurl/kurl/images/download.svg)](https://bintray.com/cioccarellia/kurl/kurl/_latestVersion)
[![CircleCI](https://circleci.com/gh/AndreaCioccarelli/Kurl/tree/master.svg?style=svg)](https://circleci.com/gh/cioccarellia/Kurl/tree/master)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/2d207f5ac27b4aed8276803b18c29115)](https://www.codacy.com/manual/cioccarellia/Kurl?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=AndreaCioccarelli/Kurl&amp;utm_campaign=Badge_Grade)
[![codecov](https://codecov.io/gh/cioccarellia/Kurl/branch/master/graph/badge.svg)](https://codecov.io/gh/cioccarellia/Kurl)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Kurl-green.svg?style=flat)](https://android-arsenal.com/details/1/8031)
[![Kotlin](https://img.shields.io/badge/Kotlin-1.3.70-orange.svg?style=flat)](https://kotlinlang.org)
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0.html)

Kurl is a library that implements, structures, modularizes and provides testability for backend APIs implementations.
It is designed to turn basic strings (and URLs) operations into consistent and idiomatic Kotlin code.

Kurl is designed from the ground up to perform all the heavy and error-prone work, leaving you with higher level abstraction and logic.
Kurl stands for _Kotlin Url Repolishing Library_. The name is inspired by [cUrl](https://curl.haxx.se).

## Setup
Kurl is available on any JVM platform (Android, Desktop, Server).
```gradle
dependencies {
    implementation 'com.cioccarellia:kurl:$version'
}
```

```xml
<dependency>
  <groupId>com.cioccarellia</groupId>
  <artifactId>kurl</artifactId>
  <version>$version</version>
  <type>pom</type>
</dependency>
```

## Introduction
If you have some backend interaction background, you can feel ok with skipping this part, it's just a technical brush up to make sure we're all on the same page.

An API url is _where_ the server hosts the service you use.
To create software which is capable of accessing a minimally structured API you have to know the API url and the scheme used by the server.

An URL is composed by several parts. 
Generally, we can group them into 3 categories:

```
https://api.github.com/users/AndreaCioccarelli/repos/Kurl
```
- Protocol: `https`
- Domain: `api.github.com`
- Web Path: `users/AndreaCioccarelli/repos/Kurl`

In the case above the root API url matches with the domain itself, but if we take:

```
https://swapi.co/api/people/3
```

The general url composition will see `swapi.co` as the domain and `api/people/3` as it's web path, and this is correct, but to be precise, the root API url is `swapi.co/api` (Because it is where all the requests are dispatched to), and the endpoint we want to access is `people/1` (because it's the path we append to the API root to access our desired endpoint).

## Kurl Docs
You use a `kurl` launcher function to start composing your request.
Kurl provides a DSL with a set of methods to shape it up.

It returns an object of `KurlRequest` type.
There are a bunch of functions and extensions you can invoke to start using Kurl:

```kotlin
val id = 3

val request = kurl("https://swapi.co/api") {
    endpoint("people/$id")
}
```

```kotlin
val r2d2Id = 3

val api = Api(
    domain = "swapi.co",
    path = "api"
)

val people = Endpoint("people")


val request1 = kurl(api, people) {
    endpoint("$r2d2Id")
}

val request2 = api.kurl(people) {
    endpoint("$r2d2Id")
}
```

Inside the lambda block you can invoke functions which will be used to compose the final request.
- `endpoint()`: Appends the passed string to the request url.
- `parameters()`: Sets the passed key-value pairs as the URL query.
- `headers()`: Sets the header value for the header key for every entry you passed.
- `fragment()`: Sets the fragment tag at the end of the url.

Once the lambda has been executed a request object is returned.
It contains all the finalized information needed to extract the required url, plus, additional useful methods for testing and checking the url.

The core library can also be used alongside a **kurl-ktx** dependency for supported HTTP clients, in order to support a bunch of specific features and to reduce the code you need to write.

### Api
An `Api` object describes a certain API root path.
It can be created either namely or directly.

_Namely_ creating an API instance means passing all the needed arguments inside the class primary constructor.
```kotlin
Api(
    domain = "swapi.co",
    path = "api"
)
```
This should be the default choice for your implementation, as it provides better readability.
Kurl is smart enough to infer and stick together the url you want to create even if you don't pass in every parameter, and to perform basic input error checking and correction.
The default protocol is HTTPS. All other fields are optional.
The only mandatory parameter is `domain`.

_Directly_ creating an API instance means that you pass in the url using the `direct()` function and that's it. 
No smart insertion or completion is performed, the precise  exact string you type in is decomposed and used as the API root url.
```kotlin
Api.direct("https://swapi.com/api")
```

### Endpoint
`Endpoint` is just a class representing a piece of an URL.
Depending on the context, Kurl internally trims and concatenates different endpoints together, basing on how you input them.

The difference between an `Api` and an `Endpoint` is that the first one is unique, and the second one can be composed (You can stick together different endpoints to reach your target configuration).

### Containers
You can make use of a `KurlApiContainer` to create a structured and specific API object.
This will group your app's API components in one place, instead of having them all scattered around your codebase.

```kotlin
class StarWarsApiContainer : KurlApiContainer() {

    val api = Api(
        domain = "swapi.co",
        path = "/api/"
        // "api", "/api" and "api/" are ok too, Kurl will remove 
        // and add any needed slash since we're doing things namely.
    )

    val lukeUrl: KurlRequest
        get() = kurl(
            Endpoint("people/1")
        )

    fun getPersonById(id: Into): String = kurl {
        endpoint("people/$id")
    }.url()

    fun getRandomStarship(): KurlRequest = kurl {
        val rid = Random().nextInt(1, 9)
        endpoint("starships/$rid")
    }
}
```
Nice touches with a `KurlApiContainer` are:
- You don't need to pass in an Api object every time you invoke `kurl`, it is automatically inferred by the context.
- You can make use of inner classes to split in logical pieces your container

# Kurl KTX
The Kurl core package is designed to build kurl requests.
If your client is supported by Kurl, you may consider including its specific KTX module, in order to simplify even more the integration process.

Each extension module contains the same exact features, adapted to the specific client syntax.
- Kurl injection on request builders.
- Conversion from Kurl requests to client specific requests.
- Library agnostic type conversion & support.

## Ktor KTX
[![Download](https://api.bintray.com/packages/cioccarellia/kurl/kurl-ktx-ktor/images/download.svg)](https://bintray.com/cioccarellia/kurl/kurl-ktx-ktor/_latestVersion)
[![Ktor](https://img.shields.io/badge/Ktor-1.3.0-purple.svg?style=flat)](https://ktor.io)
```gradle
dependencies {
    implementation 'com.cioccarellia:kurl-ktx-ktor:$version'
}
```

```xml
<dependency>
  <groupId>com.cioccarellia</groupId>
  <artifactId>kurl-ktx-ktor</artifactId>
  <version>$version</version>
  <type>pom</type>
</dependency>
```

## OkHttp KTX
[![Download](https://api.bintray.com/packages/cioccarellia/kurl/kurl-ktx-okhttp/images/download.svg)](https://bintray.com/cioccarellia/kurl/kurl-ktx-okhttp/_latestVersion)
[![OkHttp](https://img.shields.io/badge/OkHttp-4.4.0-purple.svg?style=flat)](https://square.github.com/okhttp/)
```gradle
dependencies {
    implementation 'com.cioccarellia:kurl-ktx-okhttp:$version'
}
```

```xml
<dependency>
  <groupId>com.cioccarellia</groupId>
  <artifactId>kurl-ktx-okhttp</artifactId>
  <version>$version</version>
  <type>pom</type>
</dependency>
```
