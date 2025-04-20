package com.example.todolist.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Insert
    suspend fun insert(item: ItemDataClass)

    @Update
    suspend fun update(item: ItemDataClass)

    @Delete
    suspend fun delete(item: ItemDataClass)

    @Query("SELECT * FROM items ORDER BY id ASC")
    fun getAllItems(): Flow<List<ItemDataClass>>

    @Query("SELECT * FROM items WHERE id = :itemId")
    suspend fun getItemById(itemId: Int): ItemDataClass?
}