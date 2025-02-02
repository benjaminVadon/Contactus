package org.example.utils.coroutines.dispatchers

import javax.inject.Qualifier


@Suppress("unused")
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val dispatcher: AppDispatchers)