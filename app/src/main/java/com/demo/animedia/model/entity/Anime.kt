package com.demo.animedia.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime")
data class Anime(
    @PrimaryKey(autoGenerate = false)
    val malId: Int,
    val title: String,
    val url: String,
    val profileImage: String?,
    val synopsis: String,
    val genre: String,
    val producer: String,
    val rating: String,
    val episodes: String,
    val trailer: String?
)