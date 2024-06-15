package org.example.utils.resultAdapter

import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import kotlin.reflect.javaType
import kotlin.reflect.typeOf

class ResultCallAdapterTest {

    @MockK
    lateinit var call: Call<Any>

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `ResultCallAdapter adapts Call to Call of Result`() {
        val adapter = ResultCallAdapter(String::class.java)
        val resultCall = adapter.adapt(call)
        assert(resultCall is ResultCall<*>)
    }

    @OptIn(ExperimentalStdlibApi::class)
    @Test
    fun `ResultCallAdapter as right type Call to Call of Result`() {
        val returnType = typeOf<String>().javaType
        val adapter = ResultCallAdapter(String::class.java)
        val responseType = adapter.responseType()
        assert(responseType == returnType)
    }
}