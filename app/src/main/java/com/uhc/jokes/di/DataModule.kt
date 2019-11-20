package com.uhc.jokes.di

import com.uhc.data.local.db.AppDatabase
import com.uhc.data.remote.JokeApi
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * Created by Constancio on 2019-11-16.
 */
val dataModule = module {

    single { AppDatabase.createDatabase(androidApplication()) }

    single { get<AppDatabase>().jokeDao() }

    factory { provideJokeApi(get()) }
}

fun provideJokeApi(retrofit: Retrofit): JokeApi {
    return retrofit.create(JokeApi::class.java)
}