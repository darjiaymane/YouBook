package app.project.youbook.services.Implementation;

import app.project.youbook.domain.Role;
import app.project.youbook.repositories.RoleRepository;
import app.project.youbook.services.Dto.ResponseDto;
import app.project.youbook.Enum.UserStatus;
import app.project.youbook.domain.User;
import app.project.youbook.repositories.UserRepository;
import app.project.youbook.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    ResponseDto responseDTO;
    @Override
    public ResponseDto findAll(){
        List<User> userList = userRepository.findAll();
        responseDTO.setStatus("200");
        if (userList.size() == 0){
            responseDTO.setMessage("No user found");
            return responseDTO;
        }
        responseDTO.setMessage("Users List");
        responseDTO.setData(userList);
        return responseDTO;
    }
    @Override
    public ResponseDto save(User user) {
        if (user == null || user == new User()){

            responseDTO.setStatus("404");
            responseDTO.setMessage("All Client information's are mandatory");
            return responseDTO;
        }

        if (!Arrays.toString(UserStatus.values()).contains(user.getStatus().toLowerCase())){
            responseDTO.setStatus("404");
            responseDTO.setMessage("Invalid Status");
            return responseDTO;
        }
        if(userRepository.findByEmail(user.getEmail()) != null){
            responseDTO.setStatus("404");
            responseDTO.setMessage("Email already exist");
            return responseDTO;
        }
        if (user.getRoles().size() > 0){
            user.setRoles(getRoles(user.getRoles()));
        }

        userRepository.save(user);
        responseDTO.setStatus("200");
        responseDTO.setMessage("Client has been added successfully");
        responseDTO.setData(user);
        return responseDTO;
    }

    @Override
    public ResponseDto findByStatus(UserStatus status){
        User user = userRepository.findByStatus(status);
        if (user == null){
            responseDTO.setStatus("200");
            responseDTO.setMessage("There is no User with this status");
            return responseDTO;
        }
        responseDTO.setData(user);
        return responseDTO;
    }

    @Override
    public ResponseDto findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null){
            responseDTO.setStatus("404");
            responseDTO.setMessage("User Not found");
            return responseDTO;
        }
        responseDTO.setData(user);
        return responseDTO;
    }

    @Override
    public ResponseDto findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null){
            responseDTO.setStatus("404");
            responseDTO.setMessage("User not found");
            return responseDTO;
        }
        responseDTO.setData(user);
        return responseDTO;
    }

    @Override
    public ResponseDto findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()){
            responseDTO.setStatus("200");
            responseDTO.setMessage("User not found");
            return responseDTO;
        }
        responseDTO.setData(user);
        return responseDTO;
    }

    @Override
    public ResponseDto findByFirstName(String firsName) {
        User user = userRepository.findByFirstName(firsName);
        if (user == null){
            responseDTO.setStatus("200");
            responseDTO.setMessage("User not found");
            return responseDTO;
        }
        responseDTO.setData(user);
        return responseDTO;
    }
    @Transactional
    @Override
    public ResponseDto update(User user) {
        return null;
    }

    @Override
    public ResponseDto Delete(Long id) {
        return null;
    }

    public Set<Role> getRoles(Set<Role> roleList){
        Set<Role> roles = new HashSet<>();
        for (Role role : roleList){
           roles.add(roleRepository.findByName(role.getName()));
        }
        return roles;
    }
}
