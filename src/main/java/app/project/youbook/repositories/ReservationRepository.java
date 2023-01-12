package app.project.youbook.repositories;

import app.project.youbook.domain.Reservation;
import app.project.youbook.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByRoomAndStartDateLessThanEqualAndEndDateGreaterThanEqual(Room room, Date endDate, Date startDate);
}