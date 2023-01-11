package app.project.youbook.services;

import app.project.youbook.Enum.UserStatus;
import app.project.youbook.domain.Role;
import app.project.youbook.domain.User;
import app.project.youbook.services.Dto.ResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    ResponseDto findAll();
    ResponseDto save(User user);
    ResponseDto findByEmail(String email);
    ResponseDto findByUsername(String username);
    ResponseDto findById(Long id);
    ResponseDto findByFirstName(String firsName);
    ResponseDto update(Long id, User user);
    ResponseDto login(User user);
    Role saveRole(Role role);
    ResponseDto updateStatus(Long id, String status);
    void delete(Long id);
}
