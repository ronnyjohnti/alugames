package dev.ronnyjohnti.alugames.main

import com.google.gson.Gson
import dev.ronnyjohnti.alugames.services.ApiClient

fun main() {
    val apiClient = ApiClient(Gson())
    val gamesList = apiClient.listGamesInfo()
    println(gamesList)

    println(apiClient.findGameInfo("150"))
    println(apiClient.findGameInfo("69"))
    println(apiClient.findGameInfo("518"))
    println(apiClient.findGameInfo("23"))
//    println(game)
}