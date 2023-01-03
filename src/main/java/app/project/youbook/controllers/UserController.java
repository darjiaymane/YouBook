package app.project.youbook.controllers;

import app.project.youbook.services.Dto.ResponseDto;
import app.project.youbook.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public ResponseDto findAllUsers(){
        return userService.findAll();
    }
}
