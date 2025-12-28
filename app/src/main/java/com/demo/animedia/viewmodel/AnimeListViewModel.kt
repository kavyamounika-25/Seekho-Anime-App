package com.demo.animedia.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.animedia.model.AnimeRepository
import com.demo.animedia.model.entity.Anime
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeListViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val animeRepository: AnimeRepository
) : ViewModel() {

    private val TAG = AnimeListViewModel::class.simpleName
    private val _animeList = MutableStateFlow<List<Anime>>(emptyList())
    val animeList: StateFlow<List<Anime>> get() = _animeList

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val network = connectivityManager.activeNetwork
            val capabilities = network?.let {
                connectivityManager.getNetworkCapabilities(it)?.capabilities?.contains(
                    NetworkCapabilities.NET_CAPABILITY_INTERNET
                ) ?: false
            }
            Log.d(TAG, "network capability: $capabilities")
            if (capabilities == true) {
                animeRepository.refreshAnime()
            } else {
                showToastMessage("Please check your Internet Connection")
            }

            _animeList.value = getAllAnime()
        }
    }

    fun getAllAnime() = animeRepository.getAllAnime()

    fun showToastMessage(text: String) {
        viewModelScope.launch(Dispatchers.Main) {
            Log.d(TAG, "Showing toast as text: $text")
            Toast.makeText(context, text, Toast.LENGTH_LONG).show()
        }
    }
}