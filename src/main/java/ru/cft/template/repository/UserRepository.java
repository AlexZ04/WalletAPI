package ru.cft.template.repository;

import org.springframework.stereotype.Repository;
import ru.cft.template.dto.IdResponseDto;
import ru.cft.template.dto.UserDto;

@Repository
public class UserRepository {

    public IdResponseDto createUser(UserDto user) {
        return new IdResponseDto(4);
    }

    //todo
}
