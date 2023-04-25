package cinema

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class PurchaseController {

    @PostMapping("/purchase")
    fun purchase(@RequestBody purchase: Purchase): ResponseEntity<Any> {
        val purchased = PURCHASED.find { it.row == purchase.row
                && it.column == purchase.column }
        if (purchased != null) {
            return ResponseEntity(
                mapOf(
                    "error" to "The ticket has been already purchased!"
                ),
                HttpStatus.BAD_REQUEST
            )
        }

        if (purchase.row < 1 || purchase.row > 9 ||
            purchase.column < 1 || purchase.column > 9) {
            return ResponseEntity(
                mapOf(
                    "error" to "The number of a row or a column is out of bounds!"
                ),
                HttpStatus.BAD_REQUEST
            )
        }

        val data: Purchased?
        if (purchase.row <= 4) {
            data = Purchased(purchase.row, purchase.column, 10)
            PURCHASED.add(data)
        } else {
            data = Purchased(purchase.row, purchase.column, 8)
            PURCHASED.add(data)
        }
        return ResponseEntity(data, HttpStatus.OK)
    }
}

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
class ErrorException (error: String): RuntimeException(error)
