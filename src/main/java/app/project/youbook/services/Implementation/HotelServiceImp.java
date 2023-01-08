package app.project.youbook.services.Implementation;

import app.project.youbook.domain.Hotel;
import app.project.youbook.domain.User;
import app.project.youbook.repositories.HotelRepository;
import app.project.youbook.repositories.UserRepository;
import app.project.youbook.services.Dto.ResponseDto;
import app.project.youbook.services.HotelService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

@Service
public class HotelServiceImp implements HotelService {
    private final HotelRepository hotelRepository;
    private final ResponseDto responseDto;

    private final UserRepository userRepository;

    public HotelServiceImp(HotelRepository hotelRepository, ResponseDto responseDto, UserRepository userRepository) {
        this.hotelRepository = hotelRepository;
        this.responseDto = responseDto;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseDto save(Hotel hotel) {
        if(hotel == null | Objects.equals(hotel, new Hotel())){
            responseDto.setStatus("404");
            responseDto.setMessage("Hotel cannot be null");
        }
        else{
            hotelRepository.save(hotel);
            responseDto.setStatus("200");
            responseDto.setMessage("Hotel has been added successfully");
            responseDto.setData(hotel);
        }
        return responseDto;

    }

    @Override
    public ResponseDto findAll() {
        responseDto.setData(hotelRepository.findAll());
        return responseDto;
    }

    @Override
    public ResponseDto findAllByStatus(Boolean status){
        responseDto.setData(hotelRepository.findAllByStatus(status));
        return responseDto;

    }

    @Override
    public ResponseDto findById(Long id) {
        responseDto.setData(hotelRepository.findById(id));
        return responseDto;
    }

    @Override
    public ResponseDto findByName(String name) {
        responseDto.setData(hotelRepository.findByName(name));
        return responseDto;
    }

    @Override
    public ResponseDto findByStatus(boolean status) {
        responseDto.setData(hotelRepository.findAllByStatus(status));
        return responseDto;
    }

    @Override
    public ResponseDto findByUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(value -> responseDto.setData(hotelRepository.findAllByUserId(value.getId())));
        return responseDto;
    }


    @Transactional
    @Override
    public ResponseDto update(Long id, Hotel hotel){
        if(hotel == null | Objects.equals(hotel, new Hotel())) {
            responseDto.setStatus("400");
            responseDto.setMessage("Hotel cannot be null");
            return responseDto;
        }
        Optional<Hotel> hotelToUpdate = hotelRepository.findById(hotel.getId());
        if(hotelToUpdate.isPresent()){
          hotelToUpdate.get().setName(hotel.getName());
          hotelToUpdate.get().setAddress(hotel.getAddress());
          hotelToUpdate.get().setStatus(hotel.getStatus());
          hotelToUpdate.get().setNumberOfRooms(hotel.getNumberOfRooms());
            responseDto.setStatus("200");
            responseDto.setData(hotelToUpdate);
        }
            return responseDto;

    }

}
