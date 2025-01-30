package ru.cft.template.service;

import org.springframework.http.ResponseEntity;
import ru.cft.template.dto.IdResponseDto;
import ru.cft.template.dto.user.UserCreateDto;
import ru.cft.template.dto.user.UserUpdateDto;

import java.util.UUID;

public interface UserService {

    IdResponseDto createUser(UserCreateDto user);
    ResponseEntity<?> getUserById(Long id, UUID sessionId);
    void updateUser(Long id, UserUpdateDto userUpd, UUID sessionId);
}
