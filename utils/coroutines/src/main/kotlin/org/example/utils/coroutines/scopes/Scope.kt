package org.example.utils.coroutines.scopes

import javax.inject.Qualifier

@Suppress("unused")
@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class Scope(val scope:AppCoroutineScopes)