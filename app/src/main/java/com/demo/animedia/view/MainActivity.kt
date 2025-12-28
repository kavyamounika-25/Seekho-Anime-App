package com.demo.animedia.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.demo.animedia.ui.theme.AnimediaTheme
import com.demo.animedia.viewmodel.AnimeListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnimediaTheme {
                val animeListViewModel: AnimeListViewModel = hiltViewModel<AnimeListViewModel>()

                Scaffold(modifier = Modifier.fillMaxSize()) {
                    AnimeNavGraph(viewModel = animeListViewModel)
                }
            }
        }
    }
}