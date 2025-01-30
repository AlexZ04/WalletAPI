package ru.cft.template.service;

import org.springframework.http.ResponseEntity;
import ru.cft.template.dto.IdResponseDto;
import ru.cft.template.dto.UserCreateDto;
import ru.cft.template.dto.UserDto;

public interface UserService {

    IdResponseDto createUser(UserCreateDto user);
    ResponseEntity<?> getUserById(Long id);
}
