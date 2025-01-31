package ru.cft.template.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cft.template.dto.IdResponseDto;
import ru.cft.template.dto.user.UserCreateDto;
import ru.cft.template.dto.user.UserUpdateDto;
import ru.cft.template.service.UserService;

import java.util.UUID;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("/users")
    public IdResponseDto createUser(@RequestBody UserCreateDto user) {
        return service.createUser(user);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserByUId(@PathVariable Long id, @RequestHeader UUID sessionId) {
        return service.getUserById(id, sessionId);
    }

    @PatchMapping("/users/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody UserUpdateDto user, @RequestHeader UUID sessionId) {
        service.updateUser(id, user, sessionId);
    }
}
