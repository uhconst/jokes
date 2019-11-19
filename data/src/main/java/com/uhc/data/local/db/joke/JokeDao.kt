package com.uhc.data.local.db.joke

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable

/**
 * Created by Constancio on 2019-11-16.
 */
@Dao
interface JokeDao {
    /** Inserts provided [joke]. */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertJoke(vararg joke: JokeEntity)

    /** Gets provided jokes with the given [limit]. */
    @Query("SELECT * FROM joke limit :limit")
    fun getJokes(limit: Int): Flowable<List<JokeEntity>>

    /** Deletes all jokes. */
    @Query("DELETE FROM joke")
    fun clearJokes()
}

/** Inserts provided [jokes]. */
fun JokeDao.insertJokes(jokes: List<JokeEntity>) {
    insertJoke(*jokes.toTypedArray())
}