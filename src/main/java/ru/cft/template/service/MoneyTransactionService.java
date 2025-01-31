package ru.cft.template.service;

import ru.cft.template.model.Wallet;

public interface MoneyTransactionService {
    void createTransaction(Wallet from, Wallet to, Long amount);
    void writeOffMoney(Wallet wallet, Long amount);
    void putMoney(Wallet wallet, Long amount);
}
