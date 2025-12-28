package com.demo.animedia.di

import android.content.Context
import androidx.room.Room
import com.demo.animedia.model.AnimeRepository
import com.demo.animedia.model.IAnimeRepository
import com.demo.animedia.model.animedao.AnimeDao
import com.demo.animedia.model.database.AnimeDatabase
import com.demo.animedia.model.network.INetworkRepository
import com.demo.animedia.model.network.NetworkRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAnimeRepository(animeRepository: AnimeRepository): IAnimeRepository

    @Binds
    abstract fun bindNetworkRepository(networkRepository: NetworkRepository): INetworkRepository

}

@Module
@InstallIn(SingletonComponent::class)
object AnimeModule {

    private const val BASE_URL = "https://api.jikan.moe/v4/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideAnimeDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        AnimeDatabase::class.java,
        "AnimeDatabase"
    ).build()

    @Provides
    @Singleton
    fun provideAnimeDao(animeDatabase: AnimeDatabase): AnimeDao = animeDatabase.animeDao()
}