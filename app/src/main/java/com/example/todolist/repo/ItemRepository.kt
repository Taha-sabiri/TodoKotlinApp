package com.example.todolist.data

import kotlinx.coroutines.flow.Flow

class ItemRepository(private val itemDao: ItemDao) {
    val allItems: Flow<List<ItemDataClass>> = itemDao.getAllItems()

    suspend fun insert(item: ItemDataClass) {
        itemDao.insert(item)
    }

    suspend fun update(item: ItemDataClass) {
        itemDao.update(item)
    }

    suspend fun delete(item: ItemDataClass) {
        itemDao.delete(item)
    }

    suspend fun getItemById(itemId: Int): ItemDataClass? {
        return itemDao.getItemById(itemId)
    }
}