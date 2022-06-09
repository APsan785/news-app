package com.apsan.usingkoin.di

import com.apsan.usingkoin.api.NewsApi
import com.apsan.usingkoin.repo.Repository
import com.apsan.usingkoin.viewmodel.NewsViewmodel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val repositoryModule = module {
    single { Repository(get()) }
}

val viewModelModule = module {
    viewModel {
        NewsViewmodel(get())
    }
}

val retrofitModule = module {

    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()

        return okHttpClientBuilder.build()
    }

    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(NewsApi.BASE_URL)
            .client(client)
            .build()
    }


    single {
        provideRetrofit(get())
    }
    single { provideHttpClient() }
}

val apiModule = module {

    fun provideNewsAPI(retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }

    single {
        provideNewsAPI(get())
    }
}