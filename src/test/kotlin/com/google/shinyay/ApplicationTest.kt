package com.google.shinyay

import com.google.shinyay.entity.Book
import com.google.shinyay.repository.BookRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.reactive.server.WebTestClient
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTest() {

    // Must be Field Injection
    @Autowired
    private lateinit var bookRepository: BookRepository

    @Autowired
    private lateinit var webTestClient: WebTestClient

    companion object {

        @Container
        val container = PostgreSQLContainer<Nothing>("postgres:12").apply {
            withDatabaseName("testdb")
            withUsername("scott")
            withPassword("tiger")
        }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", container::getJdbcUrl);
            registry.add("spring.datasource.password", container::getPassword);
            registry.add("spring.datasource.username", container::getUsername);
        }
    }

    @Test
    fun shouldReturnAllBooks() {

        this.bookRepository.save(Book(1L, "01", "Java"))
        this.bookRepository.save(Book(2L, "02", "Kotlin"))

        this.webTestClient.get()
                .uri("/books")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody().jsonPath("$.length()").isEqualTo(2)
                .jsonPath("$[0].id").isEqualTo(1)
                .jsonPath("$[0].isbn").isEqualTo("01")
                .jsonPath("$[0].title").isEqualTo("Java")
    }

}