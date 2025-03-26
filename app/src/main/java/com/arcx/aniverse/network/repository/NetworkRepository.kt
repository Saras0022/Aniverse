package com.arcx.aniverse.network.repository

import com.arcx.aniverse.network.models.RemoteAnimeAboutInfo
import com.arcx.aniverse.network.models.RemoteAnimeHomePage

interface NetworkRepository {
    suspend fun getAnimeHomePage(): RemoteAnimeHomePage
    suspend fun getAnimeAboutInfo(animeId: String): RemoteAnimeAboutInfo
    suspend fun getAnimeEpisodes(animeId: String)
    suspend fun getAnimeEpisodeServers(animeEpisodeId: String)
    suspend fun getAnimeEpisodeStreamingLinks(
        animeEpisodeId: String,
        server: String = "hd-1",
        category: String = "sub"
    )

    suspend fun getSearchSuggestions(query: String)
}