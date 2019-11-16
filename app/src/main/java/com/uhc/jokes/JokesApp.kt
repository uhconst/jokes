package com.uhc.jokes

import android.app.Application
import com.facebook.stetho.Stetho
import com.uhc.jokes.di.dataModule
import com.uhc.jokes.di.domainModule
import com.uhc.jokes.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Created by Constancio on 2019-11-15.
 */
class JokesApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }

        startKoin {
            androidLogger()
            androidContext(this@JokesApp)
            modules(
                dataModule,
                domainModule,
                presentationModule
            )
        }
    }
}
