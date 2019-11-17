package com.uhc.domain

import com.uhc.domain.model.Joke
import io.reactivex.Single

/**
 * Created by Constancio on 2019-11-17.
 */
interface JokeRepository {

    fun getRandomJokes(jokesQuantity: Int): Single<List<Joke>>
}