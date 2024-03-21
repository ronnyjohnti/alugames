package dev.ronnyjohnti.alugames.model

data class GameInfo(val info: InfoCheapSharkApi) {
  override fun toString(): String {
    return info.toString()
  }
}