package com.google.shinyay.entity

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Book(@Id
                var id: Long) {
}