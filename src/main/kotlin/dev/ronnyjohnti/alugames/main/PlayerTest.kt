import dev.ronnyjohnti.alugames.model.Player
import java.sql.Date
import java.time.LocalDate

fun main() {
    val player1 = Player("Ronny John", "ronny@email.co")
    println(player1)

    val player2 = Player(
        "Mr. Nobody",
        "nobody@email.co",
        LocalDate.parse("2003-04-21"),
        "newsplay"
    )
    println(player2)
}