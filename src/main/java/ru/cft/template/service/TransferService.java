package ru.cft.template.service;

import ru.cft.template.dto.transfer.TransferCreateByIdDto;
import ru.cft.template.dto.transfer.TransferCreateByPhoneDto;
import ru.cft.template.dto.transfer.TransferDto;
import ru.cft.template.model.Wallet;

import java.util.UUID;

public interface TransferService {
    Wallet findWalletById(Long id);
    Wallet findWalletByPhone(String phone);
    TransferDto createTransferById(UUID sessionId, TransferCreateByIdDto transferInfo);
    TransferDto createTransferByPhone(UUID sessionId, TransferCreateByPhoneDto transferInfo);
    TransferDto getTransactionInfo(Long transferId, UUID sessionId);
}
