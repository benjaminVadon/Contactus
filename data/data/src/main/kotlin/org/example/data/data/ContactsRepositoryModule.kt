package org.example.data.data

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface ContactsRepositoryModule {
    @Binds
    @Singleton
    fun providesContactsRepository(
        contactsRepository: ContactsRepositoryImpl
    ): ContactsRepository
}