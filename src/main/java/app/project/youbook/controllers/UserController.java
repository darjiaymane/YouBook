package app.project.youbook.controllers;

import app.project.youbook.domain.User;
import app.project.youbook.services.Dto.ResponseDto;
import app.project.youbook.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseDto findByEmailOrUsername(
            @RequestParam(value = "email", defaultValue = "") String email,
            @RequestParam(value = "username", defaultValue = "") String username){
        if (!email.isEmpty()){
            return userService.findByEmail(email);
        }
        if (!username.isEmpty()){
            return userService.findByUsername(username);
        }
        return new ResponseDto("404", "BadRequest");
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createUser(@RequestBody User user) {
        ResponseDto createdUser = userService.save(user);
        return ResponseEntity.ok(createdUser);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateUser(@PathVariable Long id, @RequestBody User user) {
        ResponseDto updatedUser = userService.update(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity<ResponseDto> getAllUsers() {
        ResponseDto users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @PutMapping("{id}/status")
    public Object updateUserStatus(@PathVariable Long id, @RequestBody User user) {
        if (user != null){
            ResponseDto updatedUser = userService.updateStatus(id, user.getStatus().toString());
            return ResponseEntity.ok(updatedUser);
        }
        return ResponseEntity.badRequest();
    }
}
