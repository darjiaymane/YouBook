package app.project.youbook.controllers;

import app.project.youbook.Enum.ReservationStatus;
import app.project.youbook.domain.Reservation;
import app.project.youbook.services.Dto.ResponseDto;
import app.project.youbook.services.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseDto createReservation(@RequestBody Reservation reservation) {
        return reservationService.createReservation(reservation);
    }

    @GetMapping
    public List<Reservation> findAll() {
        return reservationService.findAll();
    }

    @PutMapping("/{id}")
    public ResponseDto update(@PathVariable Long id, @RequestBody Reservation updatedReservation) {
        return reservationService.update(updatedReservation, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        reservationService.delete(id);
    }

    @PutMapping("/status/{id}")
    public ResponseDto changeStatus(@PathVariable Long id, @RequestBody Reservation reservation) {
        return reservationService.changeStatus(id, reservation);
    }
}
