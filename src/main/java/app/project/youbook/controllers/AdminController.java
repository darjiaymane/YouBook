package app.project.youbook.controllers;

import app.project.youbook.domain.Hotel;
import app.project.youbook.domain.User;
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

    /**
     *Hotel managements
     */
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
    @GetMapping("/hotels")
    public ResponseEntity<ResponseDto> getAllHotels() {
        ResponseDto hotels = hotelService.findAll();
        return ResponseEntity.ok(hotels);
    }

    @PutMapping("/hotels/{id}/status")
    public Object updateHotelStatus(@PathVariable Long id, @RequestBody Hotel hotel) {
        if (hotel != null){
            ResponseDto updatedHotel = hotelService.updateStatus(id, hotel.getStatus());
            return ResponseEntity.ok(updatedHotel);
        }
        return ResponseEntity.badRequest();
    }

    /*
     User Management
     */

    @PostMapping("/users")
    public ResponseEntity<ResponseDto> createUser(@RequestBody User user) {
        ResponseDto createdUser = userService.save(user);
        return ResponseEntity.ok(createdUser);
    }


    @PutMapping("/users/{id}")
    public ResponseEntity<ResponseDto> updateUser(@PathVariable Long id, @RequestBody User user) {
        ResponseDto updatedUser = userService.update(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/users")
    public ResponseEntity<ResponseDto> getAllUsers() {
        ResponseDto users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/users/{id}/status")
    public Object updateUserStatus(@PathVariable Long id, @RequestBody User user) {
        if (user != null){
            ResponseDto updatedUser = userService.updateStatus(id, user.getStatus().toString());
            return ResponseEntity.ok(updatedUser);
        }
        return ResponseEntity.badRequest();
    }

}
