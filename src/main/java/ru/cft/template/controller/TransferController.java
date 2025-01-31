package ru.cft.template.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.cft.template.dto.transfer.TransferCreateByIdDto;
import ru.cft.template.dto.transfer.TransferCreateByPhoneDto;
import ru.cft.template.dto.transfer.TransferDto;
import ru.cft.template.exception.ExceptionTexts;
import ru.cft.template.exception.SessionNotFoundException;
import ru.cft.template.exception.UnauthorizedException;
import ru.cft.template.model.Session;
import ru.cft.template.model.Wallet;
import ru.cft.template.repository.SessionRepository;
import ru.cft.template.service.SecurityService;
import ru.cft.template.service.SessionService;
import ru.cft.template.service.TransferService;

import java.util.UUID;

@RestController
@RequestMapping("api")
@AllArgsConstructor
public class TransferController {
    private final TransferService transferService;
    private final SessionService sessionService;
    private final SecurityService securityService;

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
    public void getTransactionsInfo() {

    }

    @GetMapping("/transfers/{transferId}")
    public TransferDto getTransactionInfo(@PathVariable Long transferId, @RequestHeader UUID sessionId) {
        return transferService.getTransactionInfo(transferId, sessionId);
    }
}
