package com.uhc.data.remote

import com.uhc.data.remote.dto.JokeDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Constancio on 2019-11-16.
 */
interface JokeApi {
    @GET("random/{limit}")
    fun getJokes(@Path("limit") limit: Int):  Single<ResponseWrap<JokeDto.Response>>
}