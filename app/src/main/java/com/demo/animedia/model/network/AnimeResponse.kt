package com.demo.animedia.model.network

import com.demo.animedia.model.entity.Anime

data class AnimeResponse(val data: List<AnimeData>)

data class AnimeData(
    val mal_id: Int,
    val url: String?,
    val images: ProfileImage?,
    val trailer: Trailer?,
    val title: String?,
    val episodes: Int?,
    val rating: String?,
    val synopsis: String?,
    val producers: List<Producers>?,
    val genres: List<Genres>?
)

data class ProfileImage(val jpg: ImageType)

data class ImageType(val image_url: String)

data class Trailer(val embed_url: String)

data class Producers(val name: String)

data class Genres(val name: String)

fun AnimeData.toAnime(): Anime {
    val producerNames = producers
        ?.joinToString(", ") { it.name }
        ?: ""

    val allGenres = genres
        ?.joinToString(", ") { it.name }
        ?: ""

    return Anime(
        malId = mal_id,
        url = url ?: "",
        title = title ?: "Title Not Found",
        profileImage = images?.jpg?.image_url,
        synopsis = synopsis.toString(),
        genre = allGenres,
        producer = producerNames,
        rating = rating.toString(),
        episodes = episodes.toString(),
        trailer = trailer?.embed_url
    )
}
