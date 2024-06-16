package org.example.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.example.data.database.model.ContactEntity

@Dao
interface ContactDao {
    @Query("SELECT * FROM contacts")
    fun getAll(): PagingSource<Int, ContactEntity>

    @Query("SELECT * FROM contacts WHERE uid == :id LIMIT 1")
    fun findById(id: Int): ContactEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(contactEntities: List<ContactEntity>)

    @Query("DELETE FROM contacts")
    fun deleteAll()
}