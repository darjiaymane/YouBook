package app.project.youbook.services;

import app.project.youbook.Enum.ReservationStatus;
import app.project.youbook.domain.Reservation;
import app.project.youbook.domain.User;
import app.project.youbook.services.Dto.ResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReservationService {
    ResponseDto createReservation(Reservation reservation);
    List<Reservation> findAll();
    ResponseDto update(Reservation updatedReservation, Long id);
    void delete(Long id);
    ResponseDto changeStatus(Long reservation_Id, ReservationStatus status);
}
