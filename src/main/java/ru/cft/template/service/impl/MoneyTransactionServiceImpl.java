package ru.cft.template.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cft.template.exception.ExceptionTexts;
import ru.cft.template.exception.NotEnoughMoneyException;
import ru.cft.template.exception.SelfTransactionException;
import ru.cft.template.model.Wallet;
import ru.cft.template.repository.WalletRepository;
import ru.cft.template.service.MoneyTransactionService;

@Service
@AllArgsConstructor
public class MoneyTransactionServiceImpl implements MoneyTransactionService {
    private final WalletRepository walletRepository;

    @Override
    public void createTransaction(Wallet from, Wallet to, Long amount) {

        if (from.getBalance() < amount)
            throw new NotEnoughMoneyException(ExceptionTexts.NOT_ENOUGH_MONEY);

        if (from.getId().longValue() == to.getId().longValue())
            throw new SelfTransactionException(ExceptionTexts.SELF_TRANSACTION);

        from.setBalance(from.getBalance() - amount);
        to.setBalance(from.getBalance() + amount);

        walletRepository.save(from);
        walletRepository.save(to);
    }
}
