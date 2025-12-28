package com.demo.animedia.model.network

import retrofit2.http.GET

interface AnimeAPI {

    @GET("top/anime")
    suspend fun getAnime(): AnimeResponse
}