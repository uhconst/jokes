package com.uhc.jokes.di

import com.uhc.data.local.db.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

/**
 * Created by Constancio on 2019-11-16.
 */
val dataModule = module {

    single { AppDatabase.createDatabase(androidApplication()) }

    single { get<AppDatabase>().jokeDao() }
}