package org.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import org.example.data.database.dao.ContactDao
import org.example.data.database.dao.LoadPageDao
import org.example.data.database.model.ContactEntity
import org.example.data.database.model.LoadPage

@Database(entities = [ContactEntity::class, LoadPage::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao
    abstract fun loadPageDao(): LoadPageDao
}