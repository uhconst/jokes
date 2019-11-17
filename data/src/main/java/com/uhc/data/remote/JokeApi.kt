package com.uhc.data.remote

import com.uhc.data.remote.dto.JokeDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Constancio on 2019-11-16.
 */
interface JokeApi {
    @GET("random/{jokeQuantity/") // TODO: Change random value following doc
    fun getJokes(@Path("jokeQuantity") jokeQuantity: Int):  Single<ResponseWrap<JokeDto.Response>>
}