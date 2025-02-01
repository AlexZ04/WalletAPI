package ru.cft.template.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cft.template.dto.wallet.WalletDto;
import ru.cft.template.exception.ExceptionTexts;
import ru.cft.template.exception.UserNotFoundException;
import ru.cft.template.model.Session;
import ru.cft.template.model.User;
import ru.cft.template.model.Wallet;
import ru.cft.template.repository.SessionRepository;
import ru.cft.template.repository.UserRepository;
import ru.cft.template.repository.WalletRepository;
import ru.cft.template.service.MoneyTransactionService;
import ru.cft.template.service.SecurityService;
import ru.cft.template.service.SessionService;
import ru.cft.template.service.WalletService;

import java.util.Random;
import java.util.UUID;

@Service
@AllArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;
    private final SecurityService securityService;
    private final MoneyTransactionService moneyTransactionService;

    private final Random random = new Random();

    @Override
    public WalletDto getWalletInfo(UUID sessionId) {
        Session session = securityService.getSession(sessionId);

        Wallet wallet = walletRepository.findByUser(session.getUser());

        return WalletDto.builder()
                .walletNumber(wallet.getId())
                .balance(wallet.getBalance())
                .cashback(wallet.getCashback())
                .build();
    }

    @Override
    public String hesoyam(UUID sessionId) {
        Session session = securityService.getSession(sessionId);

        if (random.nextInt(101) < 25) {
            Wallet wallet = walletRepository.findByUser(session.getUser());
            int money = random.nextInt(20) + 1;

            moneyTransactionService.putMoney(wallet, (long) money);

            return String.format("На баланс было добавлено %d д.е.\nСейчас на балансе: %d денег",
                    money, wallet.getBalance());
        }

        return "Денег добавлено не было :(";

    }

    @Override
    public String cashBackCashingOut(UUID sessionId) {
        Session session = securityService.getSession(sessionId);
        Wallet wallet = walletRepository.findByUser(session.getUser());

        moneyTransactionService.cashOutCashback(wallet);

        return String.format("Кэшбек снят. Сейчас на балансе: %d денег",
                wallet.getBalance());
    }
}
