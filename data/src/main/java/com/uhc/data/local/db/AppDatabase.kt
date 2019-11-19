package com.uhc.data.local.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.uhc.data.R
import com.uhc.data.local.db.joke.JokeDao
import com.uhc.data.local.db.joke.JokeEntity

/**
 * Created by Constancio on 2019-11-16.
 */
@Database(entities = [JokeEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        fun createDatabase(application: Application): AppDatabase {
            return Room.databaseBuilder(
                application,
                AppDatabase::class.java,
                application.getString(R.string.database_name)
            ).allowMainThreadQueries().build()
        }
    }

    abstract fun jokeDao(): JokeDao
}