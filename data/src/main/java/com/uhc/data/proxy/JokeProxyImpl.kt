package com.uhc.data.proxy

import com.uhc.data.local.db.joke.JokeDao
import com.uhc.data.local.db.joke.insertJokes
import com.uhc.data.remote.JokeApi
import com.uhc.domain.proxy.JokeProxy
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Constancio on 2019-11-19.
 */
class JokeProxyImpl(
    private val jokeApi: JokeApi,
    private val jokeDao: JokeDao
) : JokeProxy {

    override fun loadRandomJokes(limit: Int): Completable =
        jokeApi.getJokes(limit)
            .flattenAsObservable { it.value }
            .map {
                it.toJoke()
            }
            .toList()
            .flatMapCompletable {
                jokeDao.clearJokes()
                jokeDao.insertJokes(it)
                return@flatMapCompletable Completable.complete()
            }
            .subscribeOn(Schedulers.io())
}