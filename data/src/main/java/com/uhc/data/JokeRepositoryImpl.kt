package com.uhc.data

import com.uhc.data.remote.JokeApi
import com.uhc.domain.JokeRepository
import com.uhc.domain.exception.DefaultException
import com.uhc.domain.model.Joke
import io.reactivex.Single
import io.reactivex.Single.error

/**
 * Created by Constancio on 2019-11-17.
 */
class JokeRepositoryImpl(
    private val jokeApi: JokeApi
) : JokeRepository {

    override fun getRandomJokes(jokesQuantity: Int): Single<List<Joke>> {
        return jokeApi.getJokes(jokesQuantity)
            .onErrorResumeNext {
                error(DefaultException())
            }
            .map { it.value }
            .flattenAsObservable { it }
            .map {
                it.toJoke()
            }
            .toList()
    }

}