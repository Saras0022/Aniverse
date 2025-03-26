package com.arcx.aniverse.network.repository

import com.arcx.aniverse.network.models.RemoteAnimeAboutInfo
import com.arcx.aniverse.network.models.RemoteAnimeHomePage
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val httpClient: HttpClient
) : NetworkRepository {

    override suspend fun getAnimeHomePage(): RemoteAnimeHomePage {
        val response = httpClient.get("/api/v2/hianime/home")
        val result: RemoteAnimeHomePage = response.body()
        return result
    }

    override suspend fun getAnimeAboutInfo(animeId: String): RemoteAnimeAboutInfo {
        val response = httpClient.get("/api/v2/hianime/anime/$animeId")
        val result: RemoteAnimeAboutInfo = response.body()
        return result
    }

    override suspend fun getAnimeEpisodes(animeId: String) {
        val response = httpClient.get("/api/v2/hianime/anime/$animeId/episodes")
        val result: String = response.body()
        println(result)
    }

    override suspend fun getAnimeEpisodeServers(animeEpisodeId: String) {
        val response = httpClient.get {
            url("/api/v2/hianime/episode/servers")
            parameter("animeEpisodeId", animeEpisodeId)
        }
        val result: String = response.body()
        println(result)
    }

    override suspend fun getAnimeEpisodeStreamingLinks(
        animeEpisodeId: String,
        server: String,
        category: String
    ) {
        val response = httpClient.get {
            url("/api/v2/hianime/episode/sources")
            parameter("animeEpisodeId", animeEpisodeId)
            parameter("server", server)
            parameter("category", category)
        }
        val result: String = response.body()
        println(result)
    }

    override suspend fun getSearchSuggestions(query: String) {
        val response = httpClient.get {
            url("/api/v2/hianime/search/suggestion")
            parameter("q", query)
        }
        val result: String = response.body()
        println(result)
    }
}