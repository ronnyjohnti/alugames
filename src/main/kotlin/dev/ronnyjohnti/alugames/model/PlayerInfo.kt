package dev.ronnyjohnti.alugames.model

import com.google.gson.annotations.SerializedName

data class PlayerInfo(
    @SerializedName("nome") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("dataNascimento") val birthDate: String,
    @SerializedName("usuario") val user: String,
    @SerializedName("idInterno") val id: String,
)
