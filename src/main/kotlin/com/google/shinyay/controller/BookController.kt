package com.google.shinyay.controller

import com.google.shinyay.entity.Book
import com.google.shinyay.repository.BookRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BookController(val repository: BookRepository) {

    @GetMapping("/books")
    fun getAllBooks(): List<Book> = repository.findAll()
}