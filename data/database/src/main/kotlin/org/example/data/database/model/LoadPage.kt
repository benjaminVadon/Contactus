package org.example.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.example.data.database.dao.LoadableApi

@Entity(tableName = "load_page")
data class LoadPage(
    @PrimaryKey val apiName: LoadableApi,
    val loadedPage: Int?,
    val lastUpdated: Long,
)