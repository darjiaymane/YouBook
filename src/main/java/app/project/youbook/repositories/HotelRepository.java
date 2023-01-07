package app.project.youbook.repositories;

import app.project.youbook.domain.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    Hotel findByName(String name);
    List<Hotel> findAllByStatus(boolean status);
    List<Hotel> findAllByUserId(Long id);
}