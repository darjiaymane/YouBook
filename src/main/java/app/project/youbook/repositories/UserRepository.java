package app.project.youbook.repositories;

import app.project.youbook.Enum.UserStatus;
import app.project.youbook.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByFirstName(String firsName);
    User findByStatus(UserStatus Status);
    User findByEmail(String email);
    User findByUsername(String username);
}
