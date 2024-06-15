package org.example.data.database

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(
        application: Application
    ): AppDatabase = Room.databaseBuilder(
        context = application,
        klass = AppDatabase::class.java,
        name = "appDatabase"
    ).build()
}
