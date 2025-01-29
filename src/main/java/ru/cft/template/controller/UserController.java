package ru.cft.template.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.cft.template.dto.IdResponseDto;
import ru.cft.template.dto.UserDto;
import ru.cft.template.service.UserService;

@RestController
@RequestMapping("/api/User")
@AllArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("/users")
    public IdResponseDto createUser(UserDto user) {
        //todo
        return service.createUser(user);
    }

    @GetMapping("/users/{id}")
    public void getUserByUId(int id) {
        //todo
    }

    @PatchMapping("/users/{id}")
    public void updateUser(int id) {
        // todo
    }
}
