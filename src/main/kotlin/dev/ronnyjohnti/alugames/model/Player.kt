package dev.ronnyjohnti.alugames.model

import java.sql.Date
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Scanner
import kotlin.random.Random

data class Player(var name: String, var email: String) {
    var birthDate: LocalDate? = null

    var user: String? = null
        set(value) {
            field = value
            if (this.id.isNullOrBlank()) {
                this.id = this.generateId()
            }
        }

    var id: String? = null
        private set

    val foundGames = mutableListOf<Game?>()

    init {
        email = validateEmail()
        user = validateUser()
    }

    constructor(name: String, email: String, birthDate: LocalDate, user: String):
        this(name, email) {
            this.birthDate = birthDate
            this.user = user
            this.id = this.generateId()
        }

    override fun toString(): String {
        return "Player(name=$name, email=$email, birthDate=$birthDate, user=$user, id=$id)"
    }

    private fun generateId(): String {
        val tag = String.format("%04d", Random.nextInt(10000))
        return "$user#$tag"
    }

    private fun validateEmail(): String {
        val emailRegex = Regex("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}\$")
        if (!emailRegex.matches(this.email)) {
            throw IllegalArgumentException()
        }
        return this.email
    }

    private fun validateUser(): String {
        println(this.user)

        return ""
    }

    companion object {
        fun createPlayer(reader: Scanner): Player {
            println("Boas vindas ao AluGames! Vamos fazer o seu cadastro.\nDigite seu nome:")
            val name = reader.nextLine()
            println("Digite seu e-mail:")
            val email = reader.nextLine()
            println("Deseja completar seu cadastro com usuário e data de nascimento? [S/n]")
            val wannaComplete = reader.nextLine()

            if(!wannaComplete.equals("s", true) && !wannaComplete.equals("")) {
                return Player(name, email)
            }

            println("Digite o nome de usuário:")
            val user = reader.nextLine()
            println("Digite a data de nascimento: (dd-mm-yyyy)")
            val birthDate = LocalDate.parse(reader.nextLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"))

            return Player(name, email, birthDate, user)
        }
    }
}
