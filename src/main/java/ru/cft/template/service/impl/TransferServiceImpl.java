package ru.cft.template.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cft.template.dto.transfer.TransferCreateByIdDto;
import ru.cft.template.dto.transfer.TransferCreateByPhoneDto;
import ru.cft.template.dto.transfer.TransferDto;
import ru.cft.template.exception.*;
import ru.cft.template.model.Session;
import ru.cft.template.model.Transfer;
import ru.cft.template.model.Wallet;
import ru.cft.template.repository.TransferRepository;
import ru.cft.template.repository.WalletRepository;
import ru.cft.template.service.MoneyTransactionService;
import ru.cft.template.service.SecurityService;
import ru.cft.template.service.TransferService;

import java.util.UUID;

@Service
@AllArgsConstructor
public class TransferServiceImpl implements TransferService {
    private final TransferRepository transferRepository;
    private final WalletRepository walletRepository;
    private final SecurityService securityService;
    private final MoneyTransactionService moneyTransactionService;

    @Override
    public Wallet findWalletById(Long id) {
        return walletRepository.findById(id)
                .orElseThrow(() -> new WalletNotFoundException(ExceptionTexts.WALLET_NOT_FOUND));
    }

    @Override
    public Wallet findWalletByPhone(String phone) {
        return walletRepository.findByUserPhone(phone)
                .orElseThrow(() -> new WalletNotFoundException(ExceptionTexts.WALLET_NOT_FOUND));
    }

    @Override
    public TransferDto createTransferById(UUID sessionId, TransferCreateByIdDto transferInfo) {
        Session session = securityService.getSession(sessionId);

        Wallet walletTo = findWalletById(transferInfo.getWalletId());
        Wallet walletFrom = session.getUser().getWallet();

        return createTransfer(walletFrom, walletTo, transferInfo.getAmount());
    }

    @Override
    public TransferDto createTransferByPhone(UUID sessionId, TransferCreateByPhoneDto transferInfo) {
        Session session = securityService.getSession(sessionId);

        Wallet walletTo = findWalletByPhone(transferInfo.getPhone());
        Wallet walletFrom = session.getUser().getWallet();

        return createTransfer(walletFrom, walletTo, transferInfo.getAmount());
    }

    public TransferDto createTransfer(Wallet from, Wallet to, Long amount) {
        moneyTransactionService.createTransaction(from, to, amount);

        Transfer transfer = new Transfer(from, to, amount);
        transferRepository.save(transfer);

        return new TransferDto(transfer);
    }

    @Override
    public TransferDto getTransactionInfo(Long transferId, UUID sessionId) {

        Session session = securityService.getSession(sessionId);

        Transfer transfer = transferRepository.findById(transferId)
                .orElseThrow(() -> new TransferNotFoundException(ExceptionTexts.TRANSFER_NOT_FOUND));

        if (session.getUser().getId().longValue() != transfer.getFromWallet().getId().longValue())
            throw new ForbidException(ExceptionTexts.FORBID_TRANSACTION_ACCESS);

        return new TransferDto(transfer);
    }
}
