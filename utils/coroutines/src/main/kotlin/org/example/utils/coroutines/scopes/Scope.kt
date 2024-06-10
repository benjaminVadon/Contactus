package org.example.utils.coroutines.scopes

import javax.inject.Qualifier

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class Scope(val scope:AppCoroutineScopes)