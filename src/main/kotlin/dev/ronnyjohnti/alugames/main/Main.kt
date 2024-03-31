package dev.ronnyjohnti.alugames.main
import calculateAge
import com.google.gson.Gson
import dev.ronnyjohnti.alugames.model.Game
import dev.ronnyjohnti.alugames.model.Player
import dev.ronnyjohnti.alugames.services.ApiClient
import java.util.Scanner

fun main() {
    val reader = Scanner(System.`in`)
    var myGame: Game? = null
    val player = Player.createPlayer(reader)
    println("Cadastro concluído!")
    println(player)
    println("Idade do Player: " + player.birthDate?.calculateAge() + "\n")


    do {
        println("Digite o id do jogo: ")
        val id = reader.nextLine()

        val gameResult = runCatching {
            val myGameInfo = ApiClient(Gson()).findGameInfo(id)
            myGame = myGameInfo.info
        }

        gameResult.onFailure {
            println("Jogo inexistente.")
        }

        gameResult.onSuccess {
            println("Você quer adicionar uma descrição personlizada? [s/N]")
            val addDesc = reader.nextLine()
            if(addDesc.equals("s", true)) {
                println("Insira a descrição:")
               myGame?.description = reader.nextLine()
            } else {
                myGame?.description = myGame?.title
            }

            player.foundGames.add(myGame)
        }

        println("\n=========================================================")
        println("Deseja buscar um novo jogo? [S/n]")
        val findOther = reader.nextLine()
    } while(findOther.equals("s", true) || findOther.equals(""))

    println("Jogos ordenados por título: ")
    player.foundGames.sortBy {
        it?.title
    }
    player.foundGames.forEach {
        println("Title: " + it?.title)
    }

    // Filtered games when title contains "batman"
    val filteredGames = player.foundGames.filter {
        it?.title?.contains("batman", true) ?: false
    }
    println("\nJogos do Batman: ")
    println(filteredGames)

    println("\nDeseja excluir algum jogo da primeira lista? [s/N]")
    val delete = reader.nextLine()
    if(delete.equals("s", true)) {
        println("Informe a posição: ")
        val index = reader.nextInt()
        player.foundGames.removeAt(index)
    }

    println("\nLista atualizada: ")
    player.foundGames.forEach {
        println("Title: " + it?.title)
    }

    println("Busca finalizada!")
}
