package app.project.youbook.controllers;

import app.project.youbook.domain.Hotel;
import app.project.youbook.services.Dto.ResponseDto;
import app.project.youbook.services.HotelService;
import app.project.youbook.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin")
public class AdminController {
    private final HotelService hotelService;
    private final UserService userService;

    public AdminController(HotelService hotelService, UserService userService) {
        this.hotelService = hotelService;
        this.userService = userService;
    }

    @PostMapping("/hotels")
    public ResponseEntity<ResponseDto> createHotel(@RequestBody Hotel hotel) {
        ResponseDto createdHotel = hotelService.save(hotel);
        return ResponseEntity.ok(createdHotel);
    }


    @PutMapping("/hotels/{id}")
    public ResponseEntity<ResponseDto> updateHotel(@PathVariable Long id, @RequestBody Hotel hotel) {
        ResponseDto updatedHotel = hotelService.update(id, hotel);
        return ResponseEntity.ok(updatedHotel);
    }

    @DeleteMapping("/hotels/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        hotelService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
