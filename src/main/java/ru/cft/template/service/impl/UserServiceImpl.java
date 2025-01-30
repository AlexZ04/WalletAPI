package ru.cft.template.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cft.template.dto.IdResponseDto;
import ru.cft.template.dto.UserDto;
import ru.cft.template.exception.UsedCredentialsException;
import ru.cft.template.model.User;
import ru.cft.template.repository.UserRepository;
import ru.cft.template.service.UserService;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public IdResponseDto createUser(UserDto user) {

        if (repository.existsByEmail(user.getEmail())) {
            throw new UsedCredentialsException("Email already used");
        }
        if (repository.existsByEmail(user.getPhone())) {
            throw new UsedCredentialsException("Phone already used");
        }

        User newUser = new User(user);

        repository.save(newUser);

        return new IdResponseDto(newUser.getId());
    }
}
