package dev.ronnyjohnti.alugames.model

import com.google.gson.annotations.SerializedName

class Game(
  val title: String,
  val thumb: String
) {
  var description:String? = null

  override fun toString(): String {
    return "Jogo: $title \n" +
            "Capa: $thumb \n" +
            "Descrição: $description"
  }

  data class Info(
    @SerializedName("titulo") val title: String,
    @SerializedName("capa") val thumb: String,
    @SerializedName("descricao") val description: String,
    @SerializedName("preco") val price: String
  ) {
    override fun toString(): String {
      return "\nTitulo: $title" +
              "\nDescrição: $description"
    }
  }

}
