package com.google.shinyay

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.testcontainers.containers.PostgreSQLContainer

@SpringBootTest
class TestcontainersGettingStartedApplicationTests {

	companion object {

		val container = PostgreSQLContainer<Nothing>("postgres:12")
	}

}
