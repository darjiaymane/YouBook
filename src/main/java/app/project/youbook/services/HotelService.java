package app.project.youbook.services;

import app.project.youbook.domain.Hotel;
import app.project.youbook.services.Dto.ResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface HotelService {
    ResponseDto save(Hotel hotel);
    ResponseDto findAll();
    ResponseDto findById(Long id);
    ResponseDto findByName(String name);
    ResponseDto findByStatus(boolean status);
    ResponseDto findByUser(Long id);
    ResponseDto findAllByStatus(Boolean Status);
    ResponseDto update(Long id, Hotel hotel);
}
