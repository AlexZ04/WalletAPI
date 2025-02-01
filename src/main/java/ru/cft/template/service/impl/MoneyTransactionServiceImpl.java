package ru.cft.template.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cft.template.contstant.Constant;
import ru.cft.template.exception.ExceptionTexts;
import ru.cft.template.exception.ForbidException;
import ru.cft.template.exception.NotEnoughMoneyException;
import ru.cft.template.exception.SelfTransactionException;
import ru.cft.template.model.Wallet;
import ru.cft.template.repository.WalletRepository;
import ru.cft.template.service.MoneyTransactionService;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class MoneyTransactionServiceImpl implements MoneyTransactionService {
    private final WalletRepository walletRepository;

    @Override
    public void createTransaction(Wallet from, Wallet to, Long amount, boolean hasCashback) {

        if (from.getId().longValue() == to.getId().longValue())
            throw new SelfTransactionException(ExceptionTexts.SELF_TRANSACTION);

        if (from.getLastOutTransactionTime() != null
                && Math.abs(Duration.between(LocalDateTime.now(), from.getLastOutTransactionTime()).toMinutes())
                < Constant.TRANSACTION_COOLDOWN) {
            throw new ForbidException(ExceptionTexts.FORBID_TRANSACTION_TIME);
        }

        writeOffMoney(from, amount, hasCashback);
        putMoney(to, amount);
    }

    @Override
    public void writeOffMoney(Wallet wallet, Long amount, boolean cashBack) {
        if (wallet.getBalance() < amount)
            throw new NotEnoughMoneyException(ExceptionTexts.NOT_ENOUGH_MONEY);

        wallet.setBalance(wallet.getBalance() - amount);
        wallet.setLastOutTransactionTime(LocalDateTime.now());
        if (cashBack) putCashback(wallet, amount);
        walletRepository.save(wallet);
    }

    @Override
    public void putMoney(Wallet wallet, Long amount) {
        wallet.setBalance(wallet.getBalance() + amount);
        walletRepository.save(wallet);
    }

    @Override
    public void cashOutCashback(Wallet wallet) {
        if (wallet.getCashback() < 10)
            throw new NotEnoughMoneyException(ExceptionTexts.NOT_ENOUGH_CASHBACK);

        Long cashedOutMoney = calculateMoneyFromCashback(wallet.getCashback());
        putMoney(wallet, cashedOutMoney);

        wallet.setCashback(0.0);
        walletRepository.save(wallet);
    }

    @Override
    public void putCashback(Wallet wallet, Long money) {
        wallet.setCashback(wallet.getCashback() + calculateCashback(money));
        walletRepository.save(wallet);
    }

    private Long calculateMoneyFromCashback(double cashback) {
        return (long) (cashback * (1 - Constant.CASHBACK_COMMISSION_PERCENT) - Constant.CASHBACK_COMMISSION_SUM);
    }

    private double calculateCashback(Long money) {
        return money * Constant.CASHBACK_PERCENT;
    }
}
