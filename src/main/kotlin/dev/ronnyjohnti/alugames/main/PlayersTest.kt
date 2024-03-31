package dev.ronnyjohnti.alugames.main

import com.google.gson.Gson
import dev.ronnyjohnti.alugames.services.ApiClient

fun main() {
    val apiClient = ApiClient(Gson())
    val playersList = apiClient.listPlayersInfo()

    println(playersList)
}