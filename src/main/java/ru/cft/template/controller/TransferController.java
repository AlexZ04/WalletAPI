package ru.cft.template.controller;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.cft.template.dto.transfer.*;
import ru.cft.template.service.TransferService;

import java.util.UUID;

@RestController
@RequestMapping("api")
@AllArgsConstructor
public class TransferController {
    private final TransferService transferService;

    @PostMapping("/transfers/by-id")
    public TransferDto createTransactionByWalletId(@RequestHeader UUID sessionId,
                                                   @RequestBody TransferCreateByIdDto transferInfo) {
        return transferService.createTransferById(sessionId, transferInfo);
    }

    @PostMapping("/transfers/by-phone")
    public TransferDto createTransactionByPhone(@RequestHeader UUID sessionId,
                                                @RequestBody TransferCreateByPhoneDto transferInfo) {
        return transferService.createTransferByPhone(sessionId, transferInfo);
    }

    @GetMapping("/transfers")
    public TransferPageDto getTransactionsList(@RequestHeader UUID sessionId,
                                               @RequestParam(name = "transferType", required = false,
                                                       defaultValue = "BOTH") TransferType transferType,
                                               @RequestParam(name = "userId", required = false) Long userId,
                                               @RequestParam(name = "page", required = false, defaultValue = "1")
                                               @Min(1) int page,
                                               @RequestParam(name = "size", required = false, defaultValue = "5")
                                               @Min(1) int size
    ) {
        return transferService.getTransactionsList(sessionId, transferType, userId, page, size);
    }

    @GetMapping("/transfers/{transferId}")
    public TransferDto getTransactionInfo(@PathVariable Long transferId, @RequestHeader UUID sessionId) {
        return transferService.getTransactionInfo(transferId, sessionId);
    }
}
