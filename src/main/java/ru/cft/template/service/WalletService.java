package ru.cft.template.service;

import ru.cft.template.dto.MessageResponseDto;
import ru.cft.template.dto.wallet.WalletDto;

import java.util.UUID;

public interface WalletService {
    WalletDto getWalletInfo(UUID sessionId);

    MessageResponseDto hesoyam(UUID sessionId);

    MessageResponseDto cashBackCashingOut(UUID sessionId);
}
