package com.elshader.utils

sealed class Resource<out T : Any>(
) {
//    class Success<T>(data: T) : Resource<T>(data)
    class Loading() : Resource<Nothing>()
//    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)

    data class Success<out T : Any>(val data: T) : Resource<T>()
    data class Error(val exception: Exception) : Resource<Nothing>()

}