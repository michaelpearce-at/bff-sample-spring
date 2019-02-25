# BFF sample: Kotlin + Spring Boot framework
Sample spike for a BFF using Kotlin and the Spring Boot framework

## Getting Started

* Clone the repo
* Ensure java version 8 or above is available (run `java -version` to check)
* `./gradlew bootRun`

The server will listen on port 8080 by default, and will respond to the endpoint GET /bff/claimable

You must pass a legitimate X-Auth-Token header with the GET request. Use an auth token for a Tasker who has access to the instant claim removal tasks.

### Prerequisites

JDK 8+

## Built With

* [Spring Boot](https://spring.io/projects/spring-boot) - The api framework used

## Authors

* **Michael Pearce** - *Initial implementation* - [michaelpearce-at](https://github.com/michaelpearce-at)
