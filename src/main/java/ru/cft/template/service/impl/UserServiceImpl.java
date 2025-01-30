package ru.cft.template.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.cft.template.dto.IdResponseDto;
import ru.cft.template.dto.user.UserCreateDto;
import ru.cft.template.dto.user.UserDto;
import ru.cft.template.dto.user.UserShortDto;
import ru.cft.template.dto.user.UserUpdateDto;
import ru.cft.template.exception.*;
import ru.cft.template.model.Session;
import ru.cft.template.model.User;
import ru.cft.template.repository.SessionRepository;
import ru.cft.template.repository.UserRepository;
import ru.cft.template.service.SessionService;
import ru.cft.template.service.UserService;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final SessionService sessionService;

    @Override
    public IdResponseDto createUser(UserCreateDto user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UsedCredentialsException("Email already used");
        }
        if (userRepository.existsByPhone(user.getPhone())) {
            throw new UsedCredentialsException("Phone already used");
        }

        User newUser = new User(user);

        userRepository.save(newUser);

        return new IdResponseDto(newUser.getId());
    }

    @Override
    public ResponseEntity<?> getUserById(Long id, UUID sessionId) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));

        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new SessionNotFoundException("Session not found"));

        if (sessionService.checkSession(session)) {
            throw new UnauthorizedException("You session expired");
        }

        if (!Objects.equals(session.getUser().getId(), user.getId())) {
            return new ResponseEntity<>(new UserShortDto(user), HttpStatus.OK);
        }

        return new ResponseEntity<>(new UserDto(user), HttpStatus.OK);
    }

    @Override
    public void updateUser(Long id, UserUpdateDto userUpd, UUID sessionId) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));

        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new SessionNotFoundException("Session not found"));

        if (!Objects.equals(session.getUser().getId(), user.getId())) {
            throw new ForbidException("You can't edit another person profile");
        }

        if (sessionService.checkSession(session)) {
            throw new UnauthorizedException("You session expired");
        }

        user.setLastName(userUpd.getLastName());
        user.setFirstName(userUpd.getFirstName());
        user.setMiddleName(userUpd.getMiddleName());
        user.setBirthday(userUpd.getBirthday());
        user.setUpdateTime(LocalDateTime.now());

        userRepository.save(user);
    }
}
