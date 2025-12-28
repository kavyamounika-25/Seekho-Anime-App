package com.demo.animedia.model.animedao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demo.animedia.model.entity.Anime

@Dao
interface AnimeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAnime(animeList: List<Anime>)

    @Query("SELECT * FROM anime")
    fun getAllAnime(): List<Anime>

    @Query("DELETE FROM anime")
    fun clearAnime()
}