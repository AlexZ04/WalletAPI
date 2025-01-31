package ru.cft.template.service;

import ru.cft.template.dto.wallet.WalletDto;

import java.util.UUID;

public interface WalletService {
    WalletDto getWalletInfo(UUID sessionId);
    String hesoyam(UUID sessionId);
}
