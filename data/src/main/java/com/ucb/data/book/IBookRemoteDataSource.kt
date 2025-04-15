package com.ucb.data.book

import com.ucb.domain.Book

interface IBookRemoteDataSource {
    suspend fun searchByQuery(query: String) : List<Book>
}