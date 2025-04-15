package com.ucb.data

import com.ucb.data.book.IBookRemoteDataSource
import com.ucb.domain.Book

class BookRepository (
    val bookRemoteDataSource: IBookRemoteDataSource
) {
    suspend fun searchByQuery(query: String): List<Book> {
        return bookRemoteDataSource.searchByQuery(query)
    }
}