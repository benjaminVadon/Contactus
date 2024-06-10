package org.example.utils.coroutines.scopes

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.example.utils.coroutines.dispatchers.AppDispatchers.Default
import org.example.utils.coroutines.dispatchers.AppDispatchers.IO
import org.example.utils.coroutines.dispatchers.Dispatcher
import javax.inject.Singleton


@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
internal object CoroutineScopesModule {
    @Provides
    @Singleton
    @Scope(AppCoroutineScopes.Default)
    fun providesDefaultCoroutineScope(
        @Dispatcher(Default) dispatcher: CoroutineDispatcher,
    ): CoroutineScope = CoroutineScope(SupervisorJob() + dispatcher)


    @Provides
    @Scope(AppCoroutineScopes.IO)
    fun providesIOCoroutineScope(
        @Dispatcher(IO) dispatcher: CoroutineDispatcher,
    ): CoroutineScope = CoroutineScope(SupervisorJob() + dispatcher).also { it.coroutineContext }
}