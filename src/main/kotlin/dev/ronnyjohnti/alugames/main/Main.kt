package dev.ronnyjohnti.alugames.main
import dev.ronnyjohnti.alugames.model.Game
import dev.ronnyjohnti.alugames.services.ApiClient
import java.util.Scanner

fun main() {
    var myGame: Game? = null

    val reader = Scanner(System.`in`)
    println("Digite o id do jogo: ")
    val id = reader.nextLine()

    val gameResult = runCatching {
        val myGameInfo = ApiClient().findGameInfo(id)
        myGame = Game(
            myGameInfo.info.title,
            myGameInfo.info.thumb)
    }

    gameResult.onFailure {
        println("Jogo inexistente.")
        return
    }

    gameResult.onSuccess {
        println("Você quer adicionar uma descrição personlizada? [S/n]")
        val addDesc = reader.nextLine()
        if(addDesc.equals("s", true) || addDesc.equals("")) {
            println("Insira a descrição:")
           myGame?.description = reader.nextLine()
        } else {
            myGame?.description = myGame?.title
        }

        println(myGame)
    }

}