package com.ucb.ucbtest.di

import android.content.Context
import com.ucb.data.BookRepository
import com.ucb.data.book.IBookLocalDataSource
import com.ucb.data.book.IBookRemoteDataSource
import com.ucb.framework.book.BookLocalDataSource
import com.ucb.framework.book.BookRemoteDataSource
import com.ucb.framework.service.IApiService
import com.ucb.framework.service.RetrofitClient
import com.ucb.usecases.AddToMyFavorites
import com.ucb.usecases.GetMyFavoriteBooks
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
    fun provideBookRepository(remoteDataSource: IBookRemoteDataSource, localDataSource: IBookLocalDataSource): BookRepository {
        return BookRepository(remoteDataSource, localDataSource)
    }

    @Provides
    @Singleton
    fun provideSearchBooks(repository: BookRepository): SearchBooks {
        return SearchBooks(repository)
    }

    @Provides
    @Singleton
    fun provideBookLocalDataSource(@ApplicationContext context: Context) : IBookLocalDataSource {
        return BookLocalDataSource(context)
    }

    @Provides
    @Singleton
    fun provideAddToMyFavorites(repository: BookRepository): AddToMyFavorites {
        return AddToMyFavorites(repository)
    }

    @Provides
    @Singleton
    fun provideGetMyFavoriteBooks(repository: BookRepository): GetMyFavoriteBooks {
        return GetMyFavoriteBooks(repository)
    }

}
