package org.example.utils.resultAdapter

import java.io.IOException

class NoInternetConnectionException(exception: IOException) : Exception(exception)
