package app.project.youbook.services;

import app.project.youbook.domain.Address;
import app.project.youbook.services.Dto.ResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface AddressService {
    Address save(Address address);
    Address update(Address address);

}
