package dev.ronnyjohnti.alugames.services

import com.google.gson.Gson
import dev.ronnyjohnti.alugames.model.GameInfo
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class ApiClient {
    fun findGameInfo(id: String):GameInfo {
        val url = "https://www.cheapshark.com/api/1.0/games?id=$id"

        val client: HttpClient = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build()
        val response = client
            .send(request, HttpResponse.BodyHandlers.ofString())

        val json = response.body()

        val gson = Gson()
        val myGameInfo = gson.fromJson(json, GameInfo::class.java)

        return myGameInfo
    }
}