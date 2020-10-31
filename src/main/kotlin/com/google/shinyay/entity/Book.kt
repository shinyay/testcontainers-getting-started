package com.google.shinyay.entity

import javax.persistence.*

@Entity
@Table(name = "books")
data class Book(@Id
                @GeneratedValue(strategy = GenerationType.IDENTITY)
                var id: Long,
                var isbn: String,
                @Column(nullable = false)
                var title: String) {
}