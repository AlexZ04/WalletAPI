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

        if (from.getId().longValue() == to.getId().longValue())
            throw new SelfTransactionException(ExceptionTexts.SELF_TRANSACTION);

        writeOffMoney(from, amount);
        putMoney(to, amount);
    }

    @Override
    public void writeOffMoney(Wallet wallet, Long amount) {
        if (wallet.getBalance() < amount)
            throw new NotEnoughMoneyException(ExceptionTexts.NOT_ENOUGH_MONEY);

        wallet.setBalance(wallet.getBalance() - amount);
        walletRepository.save(wallet);
    }

    @Override
    public void putMoney(Wallet wallet, Long amount) {
        wallet.setBalance(wallet.getBalance() + amount);
        walletRepository.save(wallet);
    }


}
