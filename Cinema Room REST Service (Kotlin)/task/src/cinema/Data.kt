package cinema

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

class Cinema(
    val total_rows: Int,
    val total_columns: Int,
    val available_seats: MutableList<Seat>
){}

data class Seat(val row: Int, val column: Int, val price: Int)

val CINEMA = Cinema(
    9,
    9,
    mutableListOf(
        Seat(1, 1, 10),
        Seat(1, 2, 10),
        Seat(1, 3, 10),
        Seat(1, 4, 10),
        Seat(1, 5, 10),
        Seat(1, 6, 10),
        Seat(1, 7, 10),
        Seat(1, 8, 10),
        Seat(1, 9, 10),

        Seat(2, 1, 10),
        Seat(2, 2, 10),
        Seat(2, 3, 10),
        Seat(2, 4, 10),
        Seat(2, 5, 10),
        Seat(2, 6,10),
        Seat(2, 7, 10),
        Seat(2, 8,10),
        Seat(2, 9, 10),

        Seat(3, 1, 10),
        Seat(3, 2, 10),
        Seat(3, 3, 10),
        Seat(3, 4, 10),
        Seat(3, 5, 10),
        Seat(3, 6, 10),
        Seat(3, 7, 10),
        Seat(3, 8, 10),
        Seat(3, 9, 10),

        Seat(4, 1, 10),
        Seat(4, 2, 10),
        Seat(4, 3, 10),
        Seat(4, 4, 10),
        Seat(4, 5, 10),
        Seat(4, 6, 10),
        Seat(4, 7, 10),
        Seat(4, 8, 10),
        Seat(4, 9, 10),

        Seat(5, 1, 8),
        Seat(5, 2, 8),
        Seat(5, 3, 8),
        Seat(5, 4, 8),
        Seat(5, 5, 8),
        Seat(5, 6, 8),
        Seat(5, 7, 8),
        Seat(5, 8, 8),
        Seat(5, 9, 8),

        Seat(6, 1, 8),
        Seat(6, 2, 8),
        Seat(6, 3, 8),
        Seat(6, 4, 8),
        Seat(6, 5, 8),
        Seat(6, 6, 8),
        Seat(6, 7, 8),
        Seat(6, 8, 8),
        Seat(6, 9, 8),

        Seat(7, 1, 8),
        Seat(7, 2, 8),
        Seat(7, 3, 8),
        Seat(7, 4, 8),
        Seat(7, 5, 8),
        Seat(7, 6, 8),
        Seat(7, 7, 8),
        Seat(7, 8, 8),
        Seat(7, 9, 8),

        Seat(8, 1, 8),
        Seat(8, 2, 8),
        Seat(8, 3, 8),
        Seat(8, 4, 8),
        Seat(8, 5, 8),
        Seat(8, 6, 8),
        Seat(8, 7, 8),
        Seat(8, 8, 8),
        Seat(8, 9, 8),

        Seat(9, 1, 8),
        Seat(9, 2, 8),
        Seat(9, 3, 8),
        Seat(9, 4, 8),
        Seat(9, 5, 8),
        Seat(9, 6, 8),
        Seat(9, 7, 8),
        Seat(9, 8, 8),
        Seat(9, 9, 8),
    )
)

data class Token(val token: String) {
    companion object {
        @JvmStatic
        @JsonCreator
        fun fromJson(@JsonProperty("token") token: String): Token {
            return Token(token)
        }
    }
}
class Purchase(val row: Int, val column: Int)
data class Ticket(val row: Int, val column: Int, val price: Int)

data class Purchased(
    val token: String,
    val ticket: Ticket
)
var PURCHASED = mutableListOf<Purchased>()
