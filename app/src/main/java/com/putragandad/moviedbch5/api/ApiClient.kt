package com.putragandad.moviedbch5.api

import com.google.gson.GsonBuilder
import com.putragandad.moviedbch5.utils.Constant
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val interceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private val authInterceptor = Interceptor { chain ->
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .header("Authorization", "Bearer ${Constant.API_KEY}")
            .header("Accept", "application/json")
        val request = requestBuilder.build()
        chain.proceed(request)
    }

    val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .addInterceptor(authInterceptor)

    val gson = GsonBuilder()
        .setLenient()
        .create()

    var apiClient = Retrofit.Builder().baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(httpClient.build())
        .build()
        .create(ApiService::class.java)
}