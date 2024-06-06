package com.putragandad.moviedbch5.di

import com.putragandad.moviedbch5.common.network.AuthInterceptor
import com.putragandad.moviedbch5.common.Constant
import com.putragandad.moviedbch5.data.remote.ApiService
import com.putragandad.moviedbch5.helper.PrefDataStoreManager
import com.putragandad.moviedbch5.repositories.MoviesRepository
import com.putragandad.moviedbch5.repositories.RemoteDataSource
import com.putragandad.moviedbch5.ui.viewmodels.MoviesViewModel
import com.putragandad.moviedbch5.ui.viewmodels.UserViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object KoinModule {
    val appModule = module {
        single { RemoteDataSource(get()) } // Declare Remote Data Source
        single { PrefDataStoreManager(get()) } // Declare Preference Data Store Manager
    }

    val repositoryModule = module {
        factory { MoviesRepository(get()) } // Repository
    }

    val networkModule = module {
        single { AuthInterceptor() }
        single { provideInterceptor() }
        factory { provideOkHttpClient(get(), get())}
        single { provideRetrofit(get()) }
        single { provideRetrofitApi(get()) }
    }

    val viewModelModule = module {
        viewModel { MoviesViewModel(get()) }
        viewModel { UserViewModel(get()) }
    }

    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(Constant.BASE_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun provideOkHttpClient(authInterceptor: AuthInterceptor, httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(authInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    fun provideInterceptor() : HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    fun provideRetrofitApi(retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }
}