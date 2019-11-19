package com.uhc.data.repository

import com.uhc.data.local.db.joke.JokeDao
import com.uhc.data.local.db.joke.JokeEntity
import com.uhc.domain.model.Joke
import com.uhc.domain.proxy.JokeProxy
import com.uhc.domain.repository.JokeRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Constancio on 2019-11-17.
 */
class JokeRepositoryImpl(
    private val jokeDao: JokeDao,
    private val jokeProxy: JokeProxy
) : JokeRepository {

    override fun observeJokes(limit: Int): Observable<List<Joke>> =
        jokeDao.getJokes(limit)
            .toObservable()
            .map {
                it.toJoke()
            }
            .subscribeOn(Schedulers.io())

    override fun loadRandomJokes(limit: Int): Completable =
        jokeProxy.loadRandomJokes(limit)

    private fun List<JokeEntity>.toJoke(): List<Joke> =
        mapNotNull { jokeEntity ->
            Joke(
                id = jokeEntity.id,
                joke = jokeEntity.joke
            )
        }
}