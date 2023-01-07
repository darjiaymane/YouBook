package app.project.youbook.services.Implementation;

import app.project.youbook.domain.Address;
import app.project.youbook.repositories.AddressRepository;
import app.project.youbook.services.AddressService;
import app.project.youbook.services.Dto.ResponseDto;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImp implements AddressService {
    private final AddressRepository addressRepository;

    public AddressServiceImp(AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }
    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address update(Address address) {
        return addressRepository.save(address);
    }
}
