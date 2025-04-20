package com.example.todolist.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ItemDataClass::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
}