package ru.cft.template.service;

import ru.cft.template.dto.transfer.*;
import ru.cft.template.model.Transfer;
import ru.cft.template.model.Wallet;

import java.util.List;
import java.util.UUID;

public interface TransferService {
    TransferDto createTransferById(UUID sessionId, TransferCreateByIdDto transferInfo);
    TransferDto createTransferByPhone(UUID sessionId, TransferCreateByPhoneDto transferInfo);
    TransferDto getTransactionInfo(Long transferId, UUID sessionId);
    TransferPageDto getTransactionsList(UUID sessionId, TransferType transferType, Long userId, int count, int page);
}
