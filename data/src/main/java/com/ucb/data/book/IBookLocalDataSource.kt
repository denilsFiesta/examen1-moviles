package com.ucb.data.book

import com.ucb.domain.Book

interface IBookLocalDataSource {
    suspend fun saveBook(book: Book): Boolean
    suspend fun getFavoriteBooks(): List<Book>
}