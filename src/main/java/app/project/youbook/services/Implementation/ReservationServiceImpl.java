package app.project.youbook.services.Implementation;

import app.project.youbook.domain.Reservation;
import app.project.youbook.repositories.ReservationRepository;
import app.project.youbook.services.Dto.ResponseDto;
import app.project.youbook.services.ReservationService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public boolean checkAvailability(Reservation reservation) {
        // Retrieve all reservations for the same room and overlapping dates
        List<Reservation> reservations = reservationRepository.findByRoomAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                reservation.getRoom(), reservation.getEndDate(), reservation.getStartDate()
        );

        return reservations.isEmpty();
    }

    public ResponseDto createReservation(Reservation reservation){
        if(reservation != null){
            if (reservation.getStartDate().after(reservation.getEndDate())) {
                return new ResponseDto("400", "Invalid start and end date");
            }
            reservation.setUuid(UUID.randomUUID());
            // set user and room if not set before
            if (reservation.getUser() == null){
                return new ResponseDto("400", "User id not provided");
            }
            if (reservation.getRoom() == null){
                return new ResponseDto("400", "Room id not provided");
            }
            if (!checkAvailability(reservation)){
                return new ResponseDto("400", "The room is not available at this time");
            }
            reservationRepository.save(reservation);
            return new ResponseDto("200", "Reservation created successfully", reservation);
        }
        return new ResponseDto("400", "Reservation cannot be null");
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }



    @Override
    public ResponseDto update(Reservation updatedReservation, Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if (!reservation.isPresent()){
            return new ResponseDto("400", "Reservation not found");
        }
        if (!checkAvailability(updatedReservation)){
            return new ResponseDto("400", "The room is not available at this time");
        }
        if (updatedReservation.getStartDate().after(updatedReservation.getEndDate())) {
            return new ResponseDto("400", "Invalid start and end date");
        }
        reservation.get().setStartDate(updatedReservation.getStartDate());
        reservation.get().setEndDate(updatedReservation.getEndDate());
        reservation.get().setTotalPrice(updatedReservation.getTotalPrice());
        reservationRepository.save(reservation.get());
        return new ResponseDto("200", "Reservation created successfully", reservation);
    }

    @Override
    public void delete(Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        reservation.ifPresent(reservationRepository::delete);
    }
}
