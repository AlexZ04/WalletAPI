package ru.cft.template.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cft.template.dto.MessageResponseDto;
import ru.cft.template.dto.wallet.WalletDto;
import ru.cft.template.model.Session;
import ru.cft.template.model.Wallet;
import ru.cft.template.repository.WalletRepository;
import ru.cft.template.service.MoneyTransactionService;
import ru.cft.template.service.SecurityService;
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
    public MessageResponseDto hesoyam(UUID sessionId) {
        Session session = securityService.getSession(sessionId);

        if (random.nextInt(101) < 25) {
            Wallet wallet = walletRepository.findByUser(session.getUser());
            int money = random.nextInt(20) + 1;

            moneyTransactionService.putMoney(wallet, (long) money);

            return new MessageResponseDto(String.format("На баланс было добавлено %d д.е.Сейчас на балансе: %d денег",
                    money, wallet.getBalance()));
        }

        return new MessageResponseDto("Денег добавлено не было :(");

    }

    @Override
    public MessageResponseDto cashBackCashingOut(UUID sessionId) {
        Session session = securityService.getSession(sessionId);
        Wallet wallet = walletRepository.findByUser(session.getUser());

        moneyTransactionService.cashOutCashback(wallet);

        return new MessageResponseDto(String.format("Кэшбек снят. Сейчас на балансе: %d денег",
                wallet.getBalance()));
    }
}
