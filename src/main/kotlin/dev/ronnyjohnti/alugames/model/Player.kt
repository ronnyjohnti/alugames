package dev.ronnyjohnti.alugames.model

import java.sql.Date
import kotlin.random.Random

data class Player(var name: String, var email: String) {
    var birthDate: Date? = null

    var user: String? = null
        set(value) {
            field = value
            if (this.id.isNullOrBlank()) {
                this.id = this.generateId()
            }
        }

    var id: String? = null
        private set

    init {
        email = validateEmail()
        user = validateUser()
    }

    constructor(name: String, email: String, birthDate: Date, user: String):
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
}
