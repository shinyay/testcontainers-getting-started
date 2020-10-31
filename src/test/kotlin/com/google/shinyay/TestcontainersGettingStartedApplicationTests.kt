package com.google.shinyay

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@SpringBootTest
class TestcontainersGettingStartedApplicationTests {

	companion object {

		@Container
		val container = PostgreSQLContainer<Nothing>("postgres:12").also {
			it.withDatabaseName("testdb")
			it.withUsername("scott")
			it.withPassword("tiget")
		}

		@DynamicPropertySource
		fun properties(registry: DynamicPropertyRegistry) {
			registry.add("spring.datasource.url", container::getJdbcUrl)
			registry.add("spring.datasource.username", container::getUsername)
			registry.add("spring.datasource.password", container::getPassword)
		}
	}

}
