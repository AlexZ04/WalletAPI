package ru.cft.template.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cft.template.dto.wallet.WalletDto;
import ru.cft.template.repository.WalletRepository;
import ru.cft.template.service.WalletService;

import java.util.UUID;

@Service
@AllArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;

    @Override
    public WalletDto getWalletInfo(Long userId, UUID sessionId) {
        //todo
        return null;
    }

    @Override
    public void hesoyam(Long userId, UUID sessionId) {
        //todo
    }
}
