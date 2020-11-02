# Testing Spring Boot with Testcontainers

Testcontainers is a Java library that supports JUnit tests, providing lightweight, throwaway instances of common databases, Selenium web browsers, or anything else that can run in a Docker container.

## Description
### Dependency
#### Testcontainers Dependency
- org.testcontainers
  - postgresql
    - 1.15.0-rc2
  - junit-jupiter
    - 1.15.0-rc2

```kotlin
dependencies {
	testImplementation("org.testcontainers:postgresql:1.15.0-rc2")
	testImplementation("org.testcontainers:junit-jupiter:1.15.0-rc2")
}
```

#### Spring Boot Dependency
- org.springframework.boot
  - spring-boot-starter-web
  - spring-boot-starter-webflux
  - spring-boot-starter-data-jpa
- org.flywaydb
  - flyway-core
- org.postgresql
  - postgresql

### @Testcontainers
You should add `@Testcontainers` to Class to register **JUnit 5 Extensions**

```kotlin
@Testcontainers
@SpringBootTest
class TestcontainersGettingStartedApplicationTests
```

### @Container 
Define PostgreSQLContainer in **companion object**, which is Singleton.


```kotlin
companion object {
  @Container
  val container = PostgreSQLContainer<Nothing>("postgres:12")
}
```

### @DynamicPropertySource

You can configure Spring properties with `@DynamicPropertySource`

```kotlin
@JvmStatic
@DynamicPropertySource
fun properties(registry: DynamicPropertyRegistry) {
    registry.add("spring.datasource.url", container::getJdbcUrl)
}
```

#### @JvmStatin / Companion Object
When you refer to Companion object from Java, you can see it as static Companion function.
With `@JvmStatic` you can see static function without Companion.

### Test with WebTestClient

- `WebTestClient.ResponseSpec`
  - expectHeader()
  - expectCookie()
  - expectStatus()
  - expectBody()

```kotlin
this.webTestClient.get()
        .uri("/books")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().is2xxSuccessful
        .expectBody()
          .jsonPath("$.length()").isEqualTo(2)
          .jsonPath("$[0].title").isEqualTo("Java")
```

### Field Injection for Test
You should implement the dependency injection during test like follows.
Otherwise, you will get an error about parameters.

```kotlin
class ApplicationTest() {
    @Autowired
    private lateinit var bookRepository: BookRepository

    @Autowired
    private lateinit var webTestClient: WebTestClient
}
```

## Demo
### Test with Testcontainers
```shell script
$ ./gradlew clean test
```

![demo](https://user-images.githubusercontent.com/3072734/97831413-54400d00-1d13-11eb-852d-697a5fbaaee6.gif)

### BootRun
#### PostgreSQL as a Container
```shell script
$ docker run --rm -d \
  --name postgres\
  -p 5432:5432 \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres -p 5432:5432 \
  postgres:13-alpine
```
```shell script
$ docker-compose up -d
```



## Features

- feature:1
- feature:2

## Requirement

## Usage

## Installation

## Licence

Released under the [MIT license](https://gist.githubusercontent.com/shinyay/56e54ee4c0e22db8211e05e70a63247e/raw/34c6fdd50d54aa8e23560c296424aeb61599aa71/LICENSE)

## Author

[shinyay](https://github.com/shinyay)
