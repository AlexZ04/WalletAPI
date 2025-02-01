package ru.cft.template.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.cft.template.dto.MessageResponseDto;
import ru.cft.template.dto.wallet.WalletDto;
import ru.cft.template.service.WalletService;

import java.util.UUID;

@RestController
@RequestMapping("api")
@AllArgsConstructor
public class WalletController {
    private final WalletService walletService;

    @GetMapping("/wallets")
    public WalletDto getWalletInfo(@RequestHeader UUID sessionId) {
        return walletService.getWalletInfo(sessionId);
    }

    @PostMapping("/wallets/HESOYAM")
    public MessageResponseDto hesoyam(@RequestHeader UUID sessionId) {
        return walletService.hesoyam(sessionId);
    }

    @PostMapping("/wallets/cashback")
    public MessageResponseDto cashbackCashingOut(@RequestHeader UUID sessionId) {
        return walletService.cashBackCashingOut(sessionId);
    }

}
