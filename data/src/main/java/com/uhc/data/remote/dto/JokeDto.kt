package com.uhc.data.remote.dto

import com.uhc.domain.model.Joke

/**
 * Created by Constancio on 2019-11-16.
 */
sealed class JokeDto {

    data class Response(
        val id: Long,
        val joke: String
    ) {
        fun toJoke() = Joke(
            id,
            joke
        )
    }
}