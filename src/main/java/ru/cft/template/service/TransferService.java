package ru.cft.template.service;

import ru.cft.template.dto.transfer.*;

import java.util.UUID;

public interface TransferService {
    TransferDto createTransferById(UUID sessionId, TransferCreateByIdDto transferInfo);

    TransferDto createTransferByPhone(UUID sessionId, TransferCreateByPhoneDto transferInfo);

    TransferDto getTransactionInfo(Long transferId, UUID sessionId);

    TransferPageDto getTransactionsList(UUID sessionId, TransferType transferType, Long userId, int page, int size);
}
