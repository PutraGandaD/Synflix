package com.putragandad.common.utils.network

import com.putragandad.common.utils.Constant
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val reqBuilder = original.newBuilder()
            .header("Authorization", "Bearer ${Constant.API_KEY}")
            .header("Accept", "application/json")
        val request = reqBuilder.build()
        return chain.proceed(request)
    }
}