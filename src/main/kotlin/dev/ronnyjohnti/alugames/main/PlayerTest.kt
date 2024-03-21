import dev.ronnyjohnti.alugames.model.Player
import java.sql.Date

fun main() {
    val player1 = Player("Ronny John", "ronny@email.co")
    println(player1)

    val player2 = Player(
        "Mr. Nobody",
        "nobody@email.co",
        Date(1050894000000),
        "newsplay"
    )
    println(player2)
}