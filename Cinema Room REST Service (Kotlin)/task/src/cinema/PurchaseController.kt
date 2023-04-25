package cinema

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

const val TEN = 10
const val EIGHT = 8

const val NUM_HIGHEST = 9
const val NUM_LOWEST = 1

const val FRONT_ROW = 4

@RestController
class PurchaseController {

    @PostMapping("/purchase")
    fun purchase(@RequestBody purchase: Purchase): ResponseEntity<Any> {
        val purchased = PURCHASED.find { it.ticket.row == purchase.row
                && it.ticket.column == purchase.column }

        if (purchased != null) {
            return ResponseEntity(
                mapOf(
                    "error" to "The ticket has been already purchased!"
                ),
                HttpStatus.BAD_REQUEST
            )
        }

        if (purchase.row < NUM_LOWEST || purchase.row > NUM_HIGHEST ||
            purchase.column < NUM_LOWEST || purchase.column > NUM_HIGHEST) {
            return ResponseEntity(
                mapOf(
                    "error" to "The number of a row or a column is out of bounds!"
                ),
                HttpStatus.BAD_REQUEST
            )
        }

        val data: Purchased?
        if (purchase.row <= FRONT_ROW) {
            data = Purchased(
                UUID.randomUUID().toString(),
                Ticket(
                    purchase.row,
                    purchase.column, TEN)
            )
            PURCHASED.add(data)
            CINEMA.available_seats.remove(Seat(purchase.row, purchase.column, TEN))
        } else {
            data = Purchased(
                UUID.randomUUID().toString(),
                Ticket(purchase.row, purchase.column, EIGHT)
            )
            PURCHASED.add(data)
            CINEMA.available_seats.remove(Seat(purchase.row, purchase.column, EIGHT))
        }
        return ResponseEntity(data, HttpStatus.OK)
    }

    @PostMapping("/return")
    fun checkReturn(@RequestBody body: Token): ResponseEntity<Any> {
        val returnTicket = PURCHASED.find { it.token == body.token }
        if (returnTicket != null){
            val ticket: Ticket = returnTicket.ticket
            PURCHASED.remove(returnTicket)
            CINEMA.available_seats.add(Seat(
                returnTicket.ticket.row,
                returnTicket.ticket.column,
                returnTicket.ticket.price)
            )
            return ResponseEntity(
                mapOf(
                    "returned_ticket" to ticket
                ),
                HttpStatus.OK
            )
        }
        return ResponseEntity(
            mapOf(
                "error" to "Wrong token!"
            ),
            HttpStatus.BAD_REQUEST
        )
    }

    @PostMapping("/stats")
    fun checkStats(@RequestParam(required = false) password: String?): ResponseEntity<Any> {
        if (password == null) {
            return ResponseEntity(
                mapOf(
                    "error" to "The password is wrong!"
                ),
                HttpStatus.UNAUTHORIZED
            )
        } else if (password.isEmpty() || password != "super_secret") {
                return ResponseEntity(
                    mapOf(
                        "error" to "The password is wrong!"
                    ),
                    HttpStatus.UNAUTHORIZED
                )
        }

        return  ResponseEntity(
            mapOf(
                "current_income" to checkCurrentIncome(PURCHASED.toList()),
                "number_of_available_seats" to CINEMA.available_seats.count(),
                "number_of_purchased_tickets" to PURCHASED.count()
            ),
            HttpStatus.OK
        )
    }

    private fun checkCurrentIncome(purchases: List<Purchased>): Int {
       var totalPrice = 0
        for (purchase in purchases) {
            totalPrice += purchase.ticket.price
        }
        return totalPrice
    }

}

