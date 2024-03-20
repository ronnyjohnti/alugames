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
}