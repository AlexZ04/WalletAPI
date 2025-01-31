package ru.cft.template.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.cft.template.dto.wallet.WalletDto;
import ru.cft.template.service.WalletService;

import java.util.UUID;

@RestController
@RequestMapping("api")
@AllArgsConstructor
public class WalletController {
    private final WalletService walletService;

    @GetMapping("/wallets/{userId}")
    public WalletDto getWalletInfo(@PathVariable Long userId, @RequestHeader UUID sessionId) {
        return walletService.getWalletInfo(userId, sessionId);
    }

    @PostMapping("/wallets/{userId}/HESOYAM")
    public String hesoyam(@PathVariable Long userId, @RequestHeader UUID sessionId) {
        return walletService.hesoyam(userId, sessionId);
    }

}
