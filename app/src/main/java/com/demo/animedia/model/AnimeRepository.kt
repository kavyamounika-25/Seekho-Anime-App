package com.demo.animedia.model

import android.util.Log
import com.demo.animedia.model.animedao.AnimeDao
import com.demo.animedia.model.entity.Anime
import com.demo.animedia.model.network.INetworkRepository
import com.demo.animedia.model.network.toAnime
import javax.inject.Inject

interface IAnimeRepository {

    fun getAllAnime(): List<Anime>
    fun insertAnime(animeList: List<Anime>)
    fun clearAnime()
    suspend fun refreshAnime()
}

class AnimeRepository @Inject constructor(
    private val animeDao: AnimeDao,
    private val networkRepository: INetworkRepository,
) : IAnimeRepository {

    private val TAG = AnimeRepository::class.simpleName

    override fun getAllAnime() = animeDao.getAllAnime()

    override fun insertAnime(animeList: List<Anime>) = animeDao.insertAnime(animeList)

    override fun clearAnime() = animeDao.clearAnime()

    override suspend fun refreshAnime() {
        val response = networkRepository.getRetrofitInstance().getAnime()

        val animeEntities = response.data.map {
            Log.d(TAG, "Anime: $it")
            it.toAnime()
        }

        clearAnime()
        insertAnime(animeEntities)
    }

}