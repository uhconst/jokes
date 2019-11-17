package com.uhc.domain.interactor

import com.uhc.domain.JokeRepository
import com.uhc.domain.model.Joke
import io.reactivex.Single

/**
 * Created by Constancio on 2019-11-17.
 */
class GetRandomJokesUseCase(
    private val jokeRepository: JokeRepository
) {
    fun getRandomJokes(jokesQuantity: Int): Single<List<Joke>> {
        return jokeRepository.getRandomJokes(jokesQuantity)
    }
}