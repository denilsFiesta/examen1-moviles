package com.ucb.usecases

import com.ucb.data.BookRepository
import com.ucb.domain.Book

class AddToMyFavorites (
    val bookRepository: BookRepository
){
    suspend fun invoke(book: Book) {
        bookRepository.saveBook(book)
    }
}