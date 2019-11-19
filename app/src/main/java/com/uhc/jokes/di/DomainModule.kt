package com.uhc.jokes.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.uhc.data.proxy.JokeProxyImpl
import com.uhc.data.remote.JokeApi
import com.uhc.data.repository.JokeRepositoryImpl
import com.uhc.domain.interactor.GetRandomJokesUseCase
import com.uhc.domain.proxy.JokeProxy
import com.uhc.domain.repository.JokeRepository
import com.uhc.jokes.BuildConfig
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Constancio on 2019-11-16.
 */
val domainModule = module {
    factory { StethoInterceptor() }

    factory { provideOkHttpClient(get()) }

    single { provideRetrofit(get()) }

    single<JokeProxy> { JokeProxyImpl(get(), get()) }

    single<JokeRepository> { JokeRepositoryImpl(get(), get()) }

    factory { GetRandomJokesUseCase(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideOkHttpClient(stethoInterceptor: StethoInterceptor): OkHttpClient {
    return OkHttpClient()
        .newBuilder()
        .addInterceptor(stethoInterceptor)
        .build()
}

fun provideJokeApi(retrofit: Retrofit): JokeApi {
    return retrofit.create(JokeApi::class.java)
}