package app.project.youbook.services.Implementation;

import app.project.youbook.domain.Hotel;
import app.project.youbook.domain.Room;
import app.project.youbook.repositories.RoomRepository;
import app.project.youbook.services.Dto.ResponseDto;
import app.project.youbook.services.RoomService;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

public class RoomServiceImp implements RoomService {

    private final RoomRepository roomRepository;
    private final ResponseDto responseDto;

    public RoomServiceImp(RoomRepository roomRepository, ResponseDto responseDto) {
        this.roomRepository = roomRepository;
        this.responseDto = responseDto;
    }

    @Override
    public ResponseDto save(Room room) {
        if(room == null | Objects.equals(room, new Room())){
            responseDto.setStatus("404");
            responseDto.setMessage("Room cannot be null");
        }
        else{
            roomRepository.save(room);
            responseDto.setStatus("200");
            responseDto.setMessage("Room has been added successfully");
            responseDto.setData(room);
        }
        return responseDto;
    }

    @Transactional
    @Override
    public ResponseDto update(Long id, Room room){
        if(room == null | Objects.equals(room, new Room())) {
            responseDto.setStatus("400");
            responseDto.setMessage("Room cannot be null");
            return responseDto;
        }
        Optional<Room> roomToUpdate = roomRepository.findById(id);
        if(roomToUpdate.isPresent()){
            roomToUpdate.get().setRoomNumber(room.getRoomNumber());
            roomToUpdate.get().setDescription(room.getDescription());
            roomToUpdate.get().setType(room.getType());
            roomToUpdate.get().setNumberOfBeds(room.getNumberOfBeds());
            roomToUpdate.get().setCapacity(room.getCapacity());
            roomToUpdate.get().setPrice(room.getPrice());
            responseDto.setStatus("200");
            responseDto.setMessage("Updates done");
            responseDto.setData(roomToUpdate);
        }
        return responseDto;

    }

    @Override
    public void delete(Long id) {
        Optional<Room> room = roomRepository.findById(id);
        room.ifPresent(roomRepository::delete);
    }

    @Override
    public ResponseDto findAll() {

        responseDto.setData(roomRepository.findAll());
        return responseDto;
    }

    @Override
    public ResponseDto findById(Long id) {

        responseDto.setData(roomRepository.findById(id));
        return responseDto;
    }
}
