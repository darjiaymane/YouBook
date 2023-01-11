package app.project.youbook.services;

import app.project.youbook.domain.Room;
import app.project.youbook.services.Dto.ResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface RoomService {
    ResponseDto save(Room room);
    ResponseDto update(Long id, Room room);
    void delete(Long id);
    ResponseDto findAll();
    ResponseDto findById(Long id);
}
