import com.google.gson.annotations.SerializedName

class Game(
  val title: String,
  val thumb: String
) {
  val description = ""

  override fun toString(): String {
    return "Jogo: $title \n" +
            "Capa: $thumb \n" +
            "Descrição: $description"
  }
}