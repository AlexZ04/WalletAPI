package ru.cft.template.service;

import ru.cft.template.model.Wallet;

public interface MoneyTransactionService {
    void createTransaction(Wallet from, Wallet to, Long amount);
}
