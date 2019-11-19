package com.uhc.domain.proxy

import io.reactivex.Completable

/**
 * Created by Constancio on 2019-11-19.
 */
interface JokeProxy {
    fun loadRandomJokes(limit: Int): Completable
}