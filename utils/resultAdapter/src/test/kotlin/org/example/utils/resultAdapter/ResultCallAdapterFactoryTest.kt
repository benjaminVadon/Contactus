package org.example.utils.resultAdapter

import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Retrofit
import kotlin.reflect.javaType
import kotlin.reflect.typeOf

class ResultCallAdapterFactoryTest {
    @MockK
    lateinit var retrofit: Retrofit

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @OptIn(ExperimentalStdlibApi::class)
    @Test
    fun `ResultCallAdapterFactory creates ResultCallAdapter for Result type`() {
        val returnType = typeOf<Call<Result<Int>>>().javaType
        val factory = ResultCallAdapterFactory()
        val adapter = factory.get(returnType, emptyArray(), retrofit)
        assert(adapter is ResultCallAdapter)
    }
}