package org.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.example.data.database.model.LoadPage

@Dao
interface LoadPageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setNextPage(loadPage: LoadPage)

    @Query("SELECT * FROM load_page WHERE apiName= :apiName  LIMIT 1")
    suspend fun getNextPage(apiName: LoadableApi): LoadPage?

    @Query("DELETE FROM load_page WHERE apiName= :apiName")
    suspend fun delete(apiName: LoadableApi)
}