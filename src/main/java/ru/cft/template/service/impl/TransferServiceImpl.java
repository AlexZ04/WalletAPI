package ru.cft.template.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cft.template.contstant.Constant;
import ru.cft.template.dto.PaginationDto;
import ru.cft.template.dto.transfer.*;
import ru.cft.template.exception.*;
import ru.cft.template.model.Session;
import ru.cft.template.model.Transfer;
import ru.cft.template.model.Wallet;
import ru.cft.template.repository.TransferRepository;
import ru.cft.template.repository.UserRepository;
import ru.cft.template.repository.WalletRepository;
import ru.cft.template.service.MoneyTransactionService;
import ru.cft.template.service.SecurityService;
import ru.cft.template.service.TransferService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TransferServiceImpl implements TransferService {
    private final TransferRepository transferRepository;
    private final WalletRepository walletRepository;
    private final SecurityService securityService;
    private final MoneyTransactionService moneyTransactionService;
    private final UserRepository userRepository;

    public Wallet findWalletById(Long id) {
        return walletRepository.findById(id)
                .orElseThrow(() -> new WalletNotFoundException(ExceptionTexts.WALLET_NOT_FOUND));
    }

    public Wallet findWalletByPhone(String phone) {
        return walletRepository.findByUserPhone(phone)
                .orElseThrow(() -> new WalletNotFoundException(ExceptionTexts.WALLET_NOT_FOUND));
    }

    @Override
    public TransferDto createTransferById(UUID sessionId, TransferCreateByIdDto transferInfo) {
        Session session = securityService.getSession(sessionId);

        if (transferInfo.getAmount() <= 0)
            throw new ValidationException(ExceptionTexts.INVALID_MONEY_AMOUNT);

        Wallet walletTo = findWalletById(transferInfo.getWalletId());
        Wallet walletFrom = session.getUser().getWallet();

        return createTransfer(walletFrom, walletTo, transferInfo.getAmount());
    }

    @Override
    public TransferDto createTransferByPhone(UUID sessionId, TransferCreateByPhoneDto transferInfo) {
        Session session = securityService.getSession(sessionId);

        if (transferInfo.getAmount() <= 0)
            throw new ValidationException(ExceptionTexts.INVALID_MONEY_AMOUNT);

        Wallet walletTo = findWalletByPhone(transferInfo.getPhone());
        Wallet walletFrom = session.getUser().getWallet();

        return createTransfer(walletFrom, walletTo, transferInfo.getAmount());
    }

    public TransferDto createTransfer(Wallet from, Wallet to, Long amount) {
        moneyTransactionService.createTransaction(from, to, amount, hasCashback(from, to));

        Transfer transfer = new Transfer(from, to, amount, LocalDateTime.now());
        transferRepository.save(transfer);

        return TransferDto.builder()
                .transferId(transfer.getTransferId())
                .creationTime(transfer.getCreationTime())
                .amount(transfer.getAmount())
                .build();
    }

    @Override
    public TransferDto getTransactionInfo(Long transferId, UUID sessionId) {
        Session session = securityService.getSession(sessionId);

        Transfer transfer = transferRepository.findById(transferId)
                .orElseThrow(() -> new TransferNotFoundException(ExceptionTexts.TRANSFER_NOT_FOUND));

        if (session.getUser().getId().longValue() != transfer.getFromWallet().getId().longValue() ||
                session.getUser().getId().longValue() != transfer.getToWallet().getId().longValue())
            throw new ForbidException(ExceptionTexts.FORBID_TRANSACTION_ACCESS);

        return TransferDto.builder()
                .transferId(transfer.getTransferId())
                .creationTime(transfer.getCreationTime())
                .amount(transfer.getAmount())
                .build();
    }

    @Override
    public TransferPageDto getTransactionsList(UUID sessionId, TransferType transferType, Long userId,
                                               int page, int size) {
        Session session = securityService.getSession(sessionId);
        Wallet userWallet = session.getUser().getWallet();

        List<Transfer> transactions = transferRepository.findAll();

        if (transferType == TransferType.BOTH) {
            transactions = transactions.stream()
                    .filter(t -> t.getFromWallet().getId().longValue() == userWallet.getId().longValue()
                            || t.getToWallet().getId().longValue() == userWallet.getId().longValue())
                    .toList();
        } else if (transferType == TransferType.IN) {
            transactions = transactions.stream()
                    .filter(t -> t.getToWallet().getId().longValue() == userWallet.getId().longValue())
                    .toList();
        } else if (transferType == TransferType.OUT) {
            transactions = transactions.stream()
                    .filter(t -> t.getFromWallet().getId().longValue() == userWallet.getId().longValue())
                    .toList();
        }

        if (userId != null) {
            userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException(ExceptionTexts.USER_NOT_FOUND));

            transactions = transactions.stream()
                    .filter(t -> t.getFromWallet().getUser().getId().longValue() == userId ||
                            t.getToWallet().getUser().getId().longValue() == userId)
                    .toList();
        }

        return formatResult(transactions, page, size);
    }

    TransferPageDto formatResult(List<Transfer> transactions, int page, int size) {
        List<TransferDto> res = transactions.stream()
                .map(t -> TransferDto.builder()
                        .transferId(t.getTransferId())
                        .creationTime(t.getCreationTime())
                        .amount(t.getAmount())
                        .build())
                .toList();

        int amount = res.size();
        int count = (int) Math.ceil(amount * 1.0 / size);

        res = res.stream()
                .skip((long) (page - 1) * size)
                .limit(size)
                .toList();

        return TransferPageDto.builder()
                .transfers(res)
                .pagination(PaginationDto.builder()
                        .size(size)
                        .count(count)
                        .current(page)
                        .build())
                .build();
    }

    boolean hasCashback(Wallet from, Wallet to) {
        List<Transfer> transactions = transferRepository.findAll();
        transactions = transactions.stream()
                .filter(t -> t.getFromWallet().getId().longValue() == to.getId().longValue()
                        && t.getToWallet().getId().longValue() == from.getId().longValue())
                .toList();

        return transactions.isEmpty();
    }

}
