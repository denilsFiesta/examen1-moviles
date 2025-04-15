package com.ucb.usecases

import com.ucb.data.BookRepository
import com.ucb.domain.Book
import kotlinx.coroutines.delay

class SearchBooks (
    val bookRepository: BookRepository
) {
    suspend fun invoke(toSearch: String): List<Book> {
        delay(100)
        return bookRepository.searchByQuery(toSearch)
    }
}