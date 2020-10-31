package com.google.shinyay

import com.google.shinyay.repository.BookRepository
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.reactive.server.WebTestClient
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestcontainersGettingStartedApplicationTests(val repository: BookRepository,
												   val webTestClient: WebTestClient) {

	companion object {

		@Container
		val container = PostgreSQLContainer<Nothing>("postgres:12").also {
			it.withDatabaseName("testdb")
			it.withUsername("scott")
			it.withPassword("tiget")
		}

		@JvmStatic
		@DynamicPropertySource
		fun properties(registry: DynamicPropertyRegistry) {
			registry.add("spring.datasource.url", container::getJdbcUrl)
			registry.add("spring.datasource.username", container::getUsername)
			registry.add("spring.datasource.password", container::getPassword)
		}
	}

	@Test
	fun shouldReturnAllBooks() {

	}

}
