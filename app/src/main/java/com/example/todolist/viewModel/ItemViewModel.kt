package com.example.todolist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.ItemDataClass
import com.example.todolist.data.ItemRepository
import kotlinx.coroutines.launch

class ItemViewModel(private val repository: ItemRepository) : ViewModel() {
    val allItems = repository.allItems

    fun insert(item: ItemDataClass) = viewModelScope.launch {
        repository.insert(item)
    }

    fun update(item: ItemDataClass) = viewModelScope.launch {
        repository.update(item)
    }

    fun delete(item: ItemDataClass) = viewModelScope.launch {
        repository.delete(item)
    }
}