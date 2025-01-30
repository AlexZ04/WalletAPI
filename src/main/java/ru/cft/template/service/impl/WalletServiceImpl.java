package ru.cft.template.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cft.template.dto.wallet.WalletDto;
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

import java.util.UUID;

@Service
@AllArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;
    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final SessionService sessionService;

    @Override
    public WalletDto getWalletInfo(Long userId, UUID sessionId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new SessionNotFoundException("Session not found"));

        if (sessionService.checkSession(session)) {
            throw new UnauthorizedException("You session expired");
        }

        Wallet wallet = walletRepository.foundByUser(user);

        return new WalletDto(wallet);
    }

    @Override
    public void hesoyam(Long userId, UUID sessionId) {
        //todo
    }
}
