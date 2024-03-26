package dev.ronnyjohnti.alugames.main
import dev.ronnyjohnti.alugames.model.Game
import dev.ronnyjohnti.alugames.model.Player
import dev.ronnyjohnti.alugames.services.ApiClient
import java.util.Scanner

fun main() {
    val reader = Scanner(System.`in`)
    var myGame: Game? = null
    val player = Player.createPlayer(reader)
    println(player)

    do {
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

            player.foundGames.add(myGame)
//            println("==========================================================")
//            println(myGame)
        }

        println("\n=========================================================")
        println("Deseja buscar um novo jogo? [S/n]")
        val findOther = reader.nextLine()
    } while(findOther.equals("s", true) || findOther.equals(""))

    println(player.foundGames)

    println("Busca finalizada!")
}