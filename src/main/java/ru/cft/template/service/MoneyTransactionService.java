package ru.cft.template.service;

import ru.cft.template.model.Wallet;

public interface MoneyTransactionService {
    void createTransaction(Wallet from, Wallet to, Long amount, boolean hasCashback);

    void writeOffMoney(Wallet wallet, Long amount, boolean cashBack);

    void putMoney(Wallet wallet, Long amount);

    void cashOutCashback(Wallet wallet);

    void putCashback(Wallet wallet, Long money);
}
