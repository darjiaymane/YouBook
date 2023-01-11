package app.project.youbook.controllers;

import app.project.youbook.domain.Room;
import app.project.youbook.services.Dto.ResponseDto;
import app.project.youbook.services.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }
    @PostMapping
    public ResponseEntity<ResponseDto> createRoom(@RequestParam Long hotel_id,@RequestBody Room room) {
        ResponseDto createdRoom = roomService.save(hotel_id, room);
        return ResponseEntity.ok(createdRoom);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateRoom(@PathVariable Long id, @RequestBody Room room) {
        ResponseDto updatedRoom = roomService.update(id, room);
        return ResponseEntity.ok(updatedRoom);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        roomService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity<ResponseDto> getAllRooms() {
        ResponseDto rooms = roomService.findAll();
        return ResponseEntity.ok(rooms);
    }

}
