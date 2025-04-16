package com.ucb.framework.book

import com.ucb.data.NetworkResult
import com.ucb.data.book.IBookRemoteDataSource
import com.ucb.domain.Book
import com.ucb.framework.mappers.toModel
import com.ucb.framework.service.RetrofitClient

class BookRemoteDataSource (
    val retrofitServise: RetrofitClient
) : IBookRemoteDataSource {
    override suspend fun searchByQuery(query: String) : NetworkResult<List<Book>> {
        val response = retrofitServise.apiService.searchBooks(query)
        if(response.isSuccessful) {
            return NetworkResult.Success(response.body()!!.toModel())
        }else{
            return NetworkResult.Error(response.message())
        }
    }
}