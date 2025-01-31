package ru.cft.template.service.impl;

import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cft.template.dto.ResponseDto;
import ru.cft.template.dto.wallet.WalletDto;
import ru.cft.template.exception.ExceptionTexts;
import ru.cft.template.exception.SessionNotFoundException;
import ru.cft.template.exception.UnauthorizedException;
import ru.cft.template.exception.UserNotFoundException;
import ru.cft.template.model.Session;
import ru.cft.template.model.User;
import ru.cft.template.model.Wallet;
import ru.cft.template.repository.SessionRepository;
import ru.cft.template.repository.UserRepository;
import ru.cft.template.repository.WalletRepository;
import ru.cft.template.service.SessionService;
import ru.cft.template.service.WalletService;

import java.util.Random;
import java.util.UUID;

@Service
@AllArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;
    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final SessionService sessionService;

    private final Random random = new Random();

    @Override
    public WalletDto getWalletInfo(Long userId, UUID sessionId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException(ExceptionTexts.USER_NOT_FOUND));

        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new SessionNotFoundException(ExceptionTexts.SESSION_NOT_FOUND));

        if (!sessionService.checkSession(session)) {
            throw new UnauthorizedException(ExceptionTexts.SESSION_EXPIRED);
        }

        Wallet wallet = walletRepository.findByUser(user);

        return new WalletDto(wallet);
    }

    @Override
    public String hesoyam(Long userId, UUID sessionId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException(ExceptionTexts.USER_NOT_FOUND));

        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new SessionNotFoundException(ExceptionTexts.SESSION_NOT_FOUND));

        if (!sessionService.checkSession(session)) {
            throw new UnauthorizedException(ExceptionTexts.SESSION_EXPIRED);
        }

        if (random.nextInt(101) < 25) {
            Wallet wallet = walletRepository.findByUser(user);
            int money = random.nextInt(20) + 1;

            wallet.setBalance(Math.max(wallet.getBalance() + money, 0));
            walletRepository.save(wallet);

            return String.format("На баланс было добавлено %d д.е.\nСейчас на балансе: %d денег",
                    money, wallet.getBalance());
        }

        return "Денег добавлено не было :(";

    }
}
