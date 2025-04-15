package com.ucb.usecases

import com.ucb.data.BookRepository
import com.ucb.domain.Book

class GetMyFavoriteBooks (
    val bookRepository: BookRepository
) {
    suspend fun invoke() : List<Book> {
        return bookRepository.getFavoriteBooks()
    }
}