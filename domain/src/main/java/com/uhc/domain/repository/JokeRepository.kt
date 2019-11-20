package com.uhc.domain.repository

import com.uhc.domain.model.Joke
import io.reactivex.Completable
import io.reactivex.Observable

/**
 * Created by Constancio on 2019-11-17.
 */
interface JokeRepository {
    /** Returns a Observable with items emitted being Lists of [Joke]s. */
    fun observeJokes(limit: Int): Observable<List<Joke>>

    /** Makes call to update the local DB from a remote source. */
    fun loadRemoteRandomJokes(limit: Int): Completable
}