package dev.ronnyjohnti.alugames.services

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.ronnyjohnti.alugames.model.Game
import dev.ronnyjohnti.alugames.model.GameInfoCheapSharkApi
import dev.ronnyjohnti.alugames.model.PlayerInfo
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import kotlin.jvm.Throws

class ApiClient(private val gson: Gson) {

    @Throws
    fun findGameInfo(id: String): GameInfoCheapSharkApi {
        val url = "https://www.cheapshark.com/api/1.0/games"
        val parameters = mapOf("id" to id)

        val jsonString = getResponse(url, parameters)
        if(jsonString == "[]") {
            throw Exception("Jogo Inexistente")
        }

        val myGameInfo = gson
            .fromJson(jsonString, GameInfoCheapSharkApi::class.java)

        return myGameInfo
    }

    fun listPlayersInfo(): List<PlayerInfo> {
        val url = "https://raw.githubusercontent.com/jeniblodev/arquivosJson/main/gamers.json"

        val jsonString = getResponse(url)

        val listPlayersType = object : TypeToken<List<PlayerInfo>>() {}.type
        val listPlayersInfo: List<PlayerInfo> = gson.fromJson(jsonString, listPlayersType)

        return listPlayersInfo
    }

    fun listGamesInfo(): List<Game.Info> {
        val url = "https://raw.githubusercontent.com/jeniblodev/arquivosJson/main/jogos.json"
        val jsonString = getResponse(url)

        val listGamesType = object : TypeToken<List<Game.Info>>() {}.type
        val gamesInfoList: List<Game.Info> = gson.fromJson(jsonString, listGamesType)

        return gamesInfoList
    }

    private fun getResponse(
        url: String,
        getParameters: Map<String, String>? = null,
    ) : String {
        // @todo: Study how to encode URI
        var queryString = "?"
        getParameters?.forEach { parameter ->
            queryString += "${parameter.key}=${parameter.value}"
        }

        val client: HttpClient = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder()
            .uri(URI.create(url+queryString))
            .build()
        val response = client
            .send(request, HttpResponse.BodyHandlers.ofString())

        return response.body()
    }
}