package com.ucb.framework.book

import com.ucb.data.book.IBookRemoteDataSource
import com.ucb.domain.Book
import com.ucb.framework.mappers.toModel
import com.ucb.framework.service.RetrofitClient

class BookRemoteDataSource (
    val retrofitServise: RetrofitClient
) : IBookRemoteDataSource {
    override suspend fun searchByQuery(query: String) : List<Book> {
        return retrofitServise.apiService.searchBooks(query).toModel()
    }
}