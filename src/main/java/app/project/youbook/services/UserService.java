package app.project.youbook.services;

import app.project.youbook.Enum.UserStatus;
import app.project.youbook.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User save(User user);
    User findByStatus(UserStatus Status);
    User findByEmail(String email);
    User findByUsername(String username);
    User findById();
    User findByFirstName(String firsName);
    User update(User user);
    User Delete(Long id);
}
