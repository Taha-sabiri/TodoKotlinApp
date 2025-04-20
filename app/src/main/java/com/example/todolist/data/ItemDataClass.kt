package com.example.todolist.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("items")
data class ItemDataClass(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val isCheck:Boolean=false
)
