package com.demo.animedia.model.network

import retrofit2.Retrofit
import javax.inject.Inject

interface INetworkRepository {

    fun getRetrofitInstance(): AnimeAPI
}

class NetworkRepository @Inject constructor(
    private val retrofit: Retrofit
) : INetworkRepository {

    override fun getRetrofitInstance(): AnimeAPI = retrofit.create(AnimeAPI::class.java)
}