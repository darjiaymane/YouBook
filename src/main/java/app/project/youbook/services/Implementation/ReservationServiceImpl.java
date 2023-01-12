package app.project.youbook.services.Implementation;

import app.project.youbook.Enum.ReservationStatus;
import app.project.youbook.domain.Reservation;
import app.project.youbook.repositories.ReservationRepository;
import app.project.youbook.services.Dto.ResponseDto;
import app.project.youbook.services.ReservationService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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
            reservation.setUuid(UUID.randomUUID());
            Double totalPrice = calculatePrice(reservation);
            reservation.setTotalPrice(totalPrice);
            reservation.setStatus(ReservationStatus.PENDING);
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
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        if (reservation == null){
            return new ResponseDto("400", "Reservation not found");
        }
        if (updatedReservation.getStartDate().after(updatedReservation.getEndDate())) {
            return new ResponseDto("400", "Invalid start and end date");
        }
        if (!checkAvailability(updatedReservation)){
            return new ResponseDto("400", "The room is not available at this time");
        }
        reservation.setStartDate(updatedReservation.getStartDate());
        reservation.setEndDate(updatedReservation.getEndDate());
        reservation.setTotalPrice(calculatePrice(reservation));
        reservationRepository.save(reservation);
        return new ResponseDto("200", "Reservation created successfully", reservation);
    }

    @Override
    public void delete(Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        reservation.ifPresent(reservationRepository::delete);
    }

    public Double calculatePrice(Reservation reservation) {
        long diff = reservation.getEndDate().getTime() - reservation.getStartDate().getTime();
        long nights = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        Integer pricePerNight = reservation.getRoom().getPrice();
        return (double) (nights * pricePerNight);
    }

    public ResponseDto changeStatus(Long id, ReservationStatus status) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        if (reservation==null){
            return new ResponseDto("400", "Reservation not found");
        }
        reservation.setStatus(status);
        reservationRepository.save(reservation);
        return new ResponseDto("200", "Reservation created successfully", reservation);

    }
}
