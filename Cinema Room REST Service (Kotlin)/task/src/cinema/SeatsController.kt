package cinema

import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class SeatsController {
    @GetMapping("/seats")
    fun getSeats(): Cinema {
        return CINEMA
    }

    @GetMapping("/available_seats/{id}")
    fun getSeat(@PathVariable id: Int): Seat {
        return CINEMA.available_seats[id]
    }
}
