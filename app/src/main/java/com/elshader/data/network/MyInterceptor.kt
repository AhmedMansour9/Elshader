package com.elshader.data.network

import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.http.Headers

class MyInterceptor :Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request=chain.request();

        request=request?.newBuilder()
            ?.addHeader("Accept","application/json")
            ?.build()
        return chain.proceed(request)
    }
}