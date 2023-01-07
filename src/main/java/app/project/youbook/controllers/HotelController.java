package app.project.youbook.controllers;

import app.project.youbook.domain.Hotel;
import app.project.youbook.services.Dto.ResponseDto;
import app.project.youbook.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HotelController {
    @Autowired
    HotelService hotelService;


    @GetMapping("/user/hotels")
    public ResponseDto displayHotels(){
        return hotelService.findByStatus(true);
    }

    @GetMapping("/manager/hotels")
    public ResponseDto displayManagerHotels(@RequestParam Long userId){
        return hotelService.findByUser(userId);
    }

    @PostMapping("/manager/hotels/addhotel")
    public ResponseDto addHotel(@RequestBody Hotel hotel){
        return hotelService.save(hotel);
    }
}
