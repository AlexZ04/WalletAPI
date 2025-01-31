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
@RequestMapping("api")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public IdResponseDto createUser(@RequestBody UserCreateDto user) {
        return userService.createUser(user);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id, @RequestHeader UUID sessionId) {
        return userService.getUserById(id, sessionId);
    }

    @PatchMapping("/users/{id}")
    public void updateUser(@RequestBody UserUpdateDto user, @RequestHeader UUID sessionId) {
        userService.updateUser(user, sessionId);
    }
}
