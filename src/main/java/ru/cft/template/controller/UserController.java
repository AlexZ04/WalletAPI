package ru.cft.template.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cft.template.dto.IdResponseDto;
import ru.cft.template.dto.UserCreateDto;
import ru.cft.template.dto.UserDto;
import ru.cft.template.dto.UserUpdateDto;
import ru.cft.template.service.UserService;

@RestController
@RequestMapping("/api/User")
@AllArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("/users")
    public IdResponseDto createUser(@RequestBody UserCreateDto user) {
        return service.createUser(user);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserByUId(@PathVariable Long id) {
        return service.getUserById(id);
    }

    @PatchMapping("/users/{id}")
    public void updateUser(@PathVariable int id, @RequestBody UserUpdateDto user) {
        // todo
    }
}
