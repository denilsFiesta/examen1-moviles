package com.ucb.data

import com.ucb.data.book.IBookLocalDataSource
import com.ucb.data.book.IBookRemoteDataSource
import com.ucb.domain.Book

class BookRepository (
    val bookRemoteDataSource: IBookRemoteDataSource,
    val bookLocalDataSource: IBookLocalDataSource
) {
    suspend fun searchByQuery(query: String): List<Book> {
        return bookRemoteDataSource.searchByQuery(query)
    }

    suspend fun saveBook(book: Book): Boolean {
        bookLocalDataSource.saveBook(book)
        return true
    }

    suspend fun getFavoriteBooks(): List<Book> {
        return bookLocalDataSource.getFavoriteBooks()
    }
}