package com.ucb.ucbtest.di

import android.content.Context
import com.ucb.data.BookRepository
import com.ucb.data.book.IBookRemoteDataSource
import com.ucb.framework.book.BookRemoteDataSource
import com.ucb.framework.service.IApiService
import com.ucb.framework.service.RetrofitClient
import com.ucb.usecases.SearchBooks
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideRetrofitClient(): RetrofitClient = RetrofitClient

    @Provides
    @Singleton
    fun provideBookRemoteDataSource(retrofitClient: RetrofitClient): IBookRemoteDataSource {
        return BookRemoteDataSource(retrofitClient)
    }

    @Provides
    @Singleton
    fun provideBookRepository(remoteDataSource: IBookRemoteDataSource): BookRepository {
        return BookRepository(remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideSearchBooks(repository: BookRepository): SearchBooks {
        return SearchBooks(repository)
    }

}
