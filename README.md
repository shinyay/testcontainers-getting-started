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
Define PostgreSQLContainer in **companion object**

```kotlin
companion object {
  @Container
  val container = PostgreSQLContainer<Nothing>("postgres:12")
}
```

## Demo

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
