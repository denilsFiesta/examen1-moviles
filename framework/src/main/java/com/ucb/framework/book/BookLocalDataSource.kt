package com.ucb.framework.book
import android.content.Context
import com.ucb.data.book.IBookLocalDataSource
import com.ucb.domain.Book
import com.ucb.framework.mappers.toDomain
import com.ucb.framework.mappers.toEntity
import com.ucb.framework.persistence.AppRoomDatabase

class BookLocalDataSource (val context: Context) : IBookLocalDataSource {
    val bookDao = AppRoomDatabase.getDatabase(context).bookDao()
    override suspend fun saveBook(book: Book): Boolean {
        bookDao.saveBook(book.toEntity())
        return true
    }

    override suspend fun getFavoriteBooks(): List<Book> {
        return bookDao.getFavoriteBooks().map { it.toDomain()}
    }
}