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
import ru.cft.template.model.Wallet;
import ru.cft.template.model.contstant.Constant;
import ru.cft.template.repository.UserRepository;
import ru.cft.template.repository.WalletRepository;
import ru.cft.template.service.SecurityService;
import ru.cft.template.service.UserService;
import ru.cft.template.utility.SecurityUtility;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final SecurityService securityService;

    @Override
    public IdResponseDto createUser(UserCreateDto user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UsedCredentialsException(ExceptionTexts.EMAIL_ALREADY_USED);
        }
        if (userRepository.existsByPhone(user.getPhone())) {
            throw new UsedCredentialsException(ExceptionTexts.PHONE_ALREADY_USED);
        }

        User newUser = new User(
                user.getLastName(),
                user.getFirstName(),
                user.getMiddleName(),
                user.getPhone(),
                user.getEmail(),
                user.getBirthday(),
                SecurityUtility.hashPassword(user.getPassword()),
                LocalDateTime.now(),
                null
        );
        Wallet newUserWallet = new Wallet(newUser, Constant.MONEY_STARTER_PACK);
        newUser.setWallet(newUserWallet);

        userRepository.save(newUser);
        walletRepository.save(newUserWallet);

        return new IdResponseDto(newUser.getId());
    }

    @Override
    public ResponseEntity<?> getUserById(Long id, UUID sessionId) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException(ExceptionTexts.USER_NOT_FOUND));

        Session session = securityService.getSession(sessionId);

        if (session.getUser().getId().longValue() != user.getId().longValue()) {
            return new ResponseEntity<>(UserShortDto.builder()
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName().charAt(0) + ".")
                    .build(), HttpStatus.OK);
        }

        return new ResponseEntity<>(UserDto.builder()
                .lastName(user.getLastName())
                .firstName(user.getFirstName())
                .middleName(user.getMiddleName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .birthday(user.getBirthday())
                .createTime(user.getCreateTime())
                .updateTime(user.getUpdateTime())
                .build(), HttpStatus.OK);
    }

    @Override
    public void updateUser(UserUpdateDto userUpd, UUID sessionId) {
        Session session = securityService.getSession(sessionId);

        User user = session.getUser();

        user.setLastName(userUpd.getLastName());
        user.setFirstName(userUpd.getFirstName());
        user.setMiddleName(userUpd.getMiddleName());
        user.setBirthday(userUpd.getBirthday());
        user.setUpdateTime(LocalDateTime.now());

        userRepository.save(user);
    }
}
