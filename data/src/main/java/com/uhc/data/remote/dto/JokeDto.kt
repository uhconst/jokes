package com.uhc.data.remote.dto

import com.uhc.data.local.db.joke.JokeEntity

/**
 * Created by Constancio on 2019-11-16.
 */
sealed class JokeDto {

    data class Response(
        val id: Long,
        val joke: String
    ) {
        fun toJoke() = JokeEntity(
            id,
            joke
        )
    }
}