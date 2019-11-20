package com.uhc.jokes.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.uhc.data.proxy.JokeProxyImpl
import com.uhc.data.repository.JokeRepositoryImpl
import com.uhc.domain.interactor.GetRandomJokesUseCase
import com.uhc.domain.proxy.JokeProxy
import com.uhc.domain.repository.JokeRepository
import com.uhc.jokes.BuildConfig
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
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
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.API_URL)
        .client(okHttpClient)
        .build()
}

fun provideOkHttpClient(stethoInterceptor: StethoInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .addNetworkInterceptor(stethoInterceptor)
        .build()
}