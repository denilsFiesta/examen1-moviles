package com.ucb.framework.mappers

import com.ucb.domain.Book
import com.ucb.framework.dto.BookDto
import com.ucb.framework.dto.BookSearchResponseDto

fun BookDto.toDomain(): Book {
    return Book(
        authors = authorName ?: listOf("Autor desconocido"),
        title = title ?: "Título desconocido",
        publicationYear = firstPublishYear?.toString() ?: "Año desconocido"
    )
}

fun BookSearchResponseDto.toModel(): List<Book> {
    return this.docs.map { it.toDomain() }
}