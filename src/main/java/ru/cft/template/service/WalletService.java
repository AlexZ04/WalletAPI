package ru.cft.template.service;

import ru.cft.template.dto.wallet.WalletDto;

import java.util.UUID;

public interface WalletService {
    WalletDto getWalletInfo(Long userId, UUID sessionId);
    String hesoyam(Long userId, UUID sessionId);
}
