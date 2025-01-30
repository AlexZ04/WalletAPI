package ru.cft.template.service;

import org.springframework.http.ResponseEntity;
import ru.cft.template.dto.IdResponseDto;
import ru.cft.template.dto.user.UserCreateDto;

public interface UserService {

    IdResponseDto createUser(UserCreateDto user);
    ResponseEntity<?> getUserById(Long id);
}
