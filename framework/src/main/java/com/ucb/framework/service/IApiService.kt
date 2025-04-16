package com.ucb.framework.service

import com.ucb.framework.dto.BookSearchResponseDto
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response

interface IApiService {

    @GET("search.json")
    suspend fun searchBooks(
        @Query("q") query: String
    ): Response<BookSearchResponseDto>
}
