package com.putragandad.moviedbch5.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.putragandad.moviedbch5.utils.Constant
import com.putragandad.moviedbch5.utils.network.AuthInterceptor
import com.putragandad.moviedbch5.data.network.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {
    val networkModule = module {
        single { AuthInterceptor() }
        single { ChuckerInterceptor(get()) }
        single { provideInterceptor() }
        factory { provideOkHttpClient(get(), get(), get()) }
        single { provideRetrofit(get()) }
        single { provideRetrofitApi(get()) }
    }

    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(Constant.BASE_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun provideOkHttpClient(authInterceptor: AuthInterceptor, httpLoggingInterceptor: HttpLoggingInterceptor, chuckerInterceptor: ChuckerInterceptor): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(authInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(chuckerInterceptor)
            .build()
    }

    fun provideInterceptor() : HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    fun provideRetrofitApi(retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }
}