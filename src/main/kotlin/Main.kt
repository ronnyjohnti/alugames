
import com.google.gson.Gson
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers
import java.util.Scanner

fun main() {
    val reader = Scanner(System.`in`)
    println("Digite o id do jogo: ")
    val id = reader.nextLine()

    val url = "https://www.cheapshark.com/api/1.0/games?id=$id"

    val client: HttpClient = HttpClient.newHttpClient()
    val request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .build()
    val response = client
        .send(request, BodyHandlers.ofString())

    val json = response.body()

    val gson = Gson()

    var myGame:Game? = null

    val result = runCatching {
        val myGameInfo = gson.fromJson(json, GameInfo::class.java)
        myGame = Game(
            myGameInfo.info.title,
            myGameInfo.info.thumb)
    }

    result.onFailure {
        println("Jogo inexistente.")
    }

    result.onSuccess {
        println("Você quer adicionar uma descrição personlizada? [S/n]")
        val addDesc = reader.nextLine()
        if(addDesc.equals("s", true) || addDesc.equals("")) {
            println("Insira a descrição:")
            myGame?.description = reader.nextLine()
        }

        println(myGame)
    }

}