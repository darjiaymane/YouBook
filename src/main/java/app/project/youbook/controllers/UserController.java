package app.project.youbook.controllers;

import app.project.youbook.domain.User;
import app.project.youbook.services.Dto.ResponseDto;
import app.project.youbook.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseDto findAllUsers(){
        return userService.findAll();
    }
    @PostMapping("/addUser")
    public ResponseDto addUser(@RequestBody User user){
        return userService.save(user);
    }

    @GetMapping("/user")
    public ResponseDto findByEmail(@RequestParam(value = "email", defaultValue = "") String email){
        return userService.findByEmail(email);
    }
}
