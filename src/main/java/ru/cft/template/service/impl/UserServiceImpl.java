package ru.cft.template.service.impl;

import org.springframework.stereotype.Service;
import ru.cft.template.dto.IdResponseDto;
import ru.cft.template.dto.UserDto;
import ru.cft.template.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public IdResponseDto createUser(UserDto user) {
        return null;
    }
}
