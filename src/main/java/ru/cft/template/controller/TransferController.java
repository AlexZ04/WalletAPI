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
import ru.cft.template.service.SessionService;
import ru.cft.template.service.TransferService;

import java.util.UUID;

@RestController
@RequestMapping("api")
@AllArgsConstructor
public class TransferController {
    private final TransferService transferService;
    private final SessionService sessionService;
    private final SessionRepository sessionRepository;

    @PostMapping("/transfers/by-id")
    public TransferDto createTransactionByWalletId(@RequestHeader UUID sessionId,
                                                   @RequestBody TransferCreateByIdDto transferInfo) {

        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new SessionNotFoundException(ExceptionTexts.SESSION_NOT_FOUND));
        if (!sessionService.checkSession(session)) {
            throw new UnauthorizedException(ExceptionTexts.SESSION_EXPIRED);
        }

        Wallet walletTo = transferService.findWalletById(transferInfo.getWalletId());
        Wallet walletFrom = session.getUser().getWallet();

        return transferService.createTransfer(walletFrom, walletTo, transferInfo.getAmount());
    }

    @PostMapping("/transfers/by-phone")
    public TransferDto createTransactionByPhone(@RequestHeader UUID sessionId,
                                                @RequestBody TransferCreateByPhoneDto transferInfo) {

        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new SessionNotFoundException(ExceptionTexts.SESSION_NOT_FOUND));
        if (!sessionService.checkSession(session)) {
            throw new UnauthorizedException(ExceptionTexts.SESSION_EXPIRED);
        }

        Wallet walletTo = transferService.findWalletByPhone(transferInfo.getPhone());
        Wallet walletFrom = session.getUser().getWallet();

        return transferService.createTransfer(walletFrom, walletTo, transferInfo.getAmount());
    }

    @GetMapping("/transfers")
    public void getTransactionsInfo() {

    }

    @GetMapping("/transfers/{transferId}")
    public TransferDto getTransactionInfo(@PathVariable Long transferId, @RequestHeader UUID sessionId) {
        return transferService.getTransactionInfo(transferId, sessionId);
    }
}
