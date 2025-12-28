package com.demo.animedia.view

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import coil3.compose.AsyncImage
import com.demo.animedia.model.entity.Anime
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun AnimeDetailScreen(animeId: Int, animeList: List<Anime>) {

    val anime = animeList.first { it.malId == animeId }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 4.dp, top = 20.dp, end = 4.dp, bottom = 20.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
    ) {

        anime.trailer?.let {
            AnimeTrailerPlayer(it)
        } ?: AsyncImage(
            model = anime.profileImage,
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            contentDescription = null,
            alignment = Alignment.Center,
            contentScale = ContentScale.FillBounds
        )

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = anime.title,
            fontSize = 38.sp,
            style = MaterialTheme.typography.titleMedium,
            lineHeight = 40.sp,
            modifier = Modifier
                .wrapContentSize()
                .padding(12.dp)
        )

        Text(
            text = "Rating: ${anime.rating}",
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Text(
            text = "Genre(s): ${anime.genre}",
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Text(
            text = "Episodes: ${anime.episodes}",
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Text(
            text = "Production: ${anime.producer}",
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Text(
            text = "Description: ${anime.synopsis}",
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

@Composable
fun AnimeTrailerPlayer(trailerUrl: String) {

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val mContext = LocalContext.current
        val lifecycleOwner = LocalLifecycleOwner.current

        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            factory = {
                YouTubePlayerView(mContext).apply {
                    lifecycleOwner.lifecycle.addObserver(this)
                    enableAutomaticInitialization = false

                    addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            youTubePlayer.cueVideo(trailerUrl, 0f)
                        }

                        override fun onError(
                            youTubePlayer: YouTubePlayer,
                            error: PlayerConstants.PlayerError
                        ) {
                            super.onError(youTubePlayer, error)
                            Toast.makeText(mContext, "Unable to play video", Toast.LENGTH_LONG)
                                .show()
                        }
                    })
                }
            }
        )
    }
}
