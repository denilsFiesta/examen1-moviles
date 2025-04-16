package com.ucb.usecases

import com.ucb.data.BookRepository
import com.ucb.data.NetworkResult
import com.ucb.domain.Book
import kotlinx.coroutines.delay

class SearchBooks (
    val bookRepository: BookRepository
) {
    suspend fun invoke(toSearch: String): NetworkResult<List<Book>> {
        delay(100)
        return bookRepository.searchByQuery(toSearch)
    }
}