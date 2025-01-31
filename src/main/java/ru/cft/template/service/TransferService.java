package ru.cft.template.service;

import ru.cft.template.dto.transfer.TransferDto;
import ru.cft.template.model.Wallet;

import java.util.UUID;

public interface TransferService {
    Wallet findWalletById(Long id);
    Wallet findWalletByPhone(String phone);
    TransferDto createTransfer(Wallet from, Wallet to, Long amount);
    TransferDto getTransactionInfo(Long transferId, UUID sessionId);
}
