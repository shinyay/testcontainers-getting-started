package com.google.shinyay

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
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
	}

}
