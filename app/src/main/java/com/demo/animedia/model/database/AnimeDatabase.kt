package com.demo.animedia.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.demo.animedia.model.animedao.AnimeDao
import com.demo.animedia.model.entity.Anime

@Database(entities = [Anime::class], version = 1, exportSchema = false)
abstract class AnimeDatabase : RoomDatabase() {

    abstract fun animeDao(): AnimeDao
}