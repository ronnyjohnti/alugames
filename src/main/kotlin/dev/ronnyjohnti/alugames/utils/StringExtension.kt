import java.time.LocalDate
import java.time.Period

fun LocalDate.calculateAge(): Int {
    var age = 0
    age = Period.between(this, LocalDate.now()).years
    return age
}
