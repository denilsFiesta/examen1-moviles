package com.ucb.framework.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface IBookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE) // para repes
    suspend fun saveBook(book: BookEntity)
}