package ru.cft.template.service;

import org.springframework.stereotype.Service;
import ru.cft.template.dto.IdResponseDto;
import ru.cft.template.dto.UserDto;

public interface UserService {

    IdResponseDto createUser(UserDto user);
    //todo
}
