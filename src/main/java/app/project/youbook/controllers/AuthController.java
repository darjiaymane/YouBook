package app.project.youbook.controllers;

import app.project.youbook.domain.User;
import app.project.youbook.services.Dto.ResponseDto;
import app.project.youbook.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/l")
public class AuthController {
    final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/new")
    public ResponseDto signUp(@RequestBody User user){
        return userService.save(user);
    }
    @PostMapping
    public ResponseDto login(@RequestBody User user){
        return userService.login(user);
    }
}
