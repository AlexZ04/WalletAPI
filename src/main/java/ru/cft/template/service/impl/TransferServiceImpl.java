package ru.cft.template.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cft.template.dto.transfer.TransferDto;
import ru.cft.template.exception.*;
import ru.cft.template.model.Session;
import ru.cft.template.model.Transfer;
import ru.cft.template.model.Wallet;
import ru.cft.template.repository.SessionRepository;
import ru.cft.template.repository.TransferRepository;
import ru.cft.template.repository.WalletRepository;
import ru.cft.template.service.SessionService;
import ru.cft.template.service.TransferService;

import java.util.UUID;

@Service
@AllArgsConstructor
public class TransferServiceImpl implements TransferService {
    private final TransferRepository transferRepository;
    private final WalletRepository walletRepository;
    private final SessionRepository sessionRepository;
    private final SessionService sessionService;

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
    public TransferDto createTransfer(Wallet from, Wallet to, Long amount) {

        if (from.getBalance() < amount) {
            throw new NotEnoughMoneyException(ExceptionTexts.NOT_ENOUGH_MONEY);
        }

        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);

        Transfer transfer = new Transfer(from, to, amount);
        transferRepository.save(transfer);
        walletRepository.save(from);
        walletRepository.save(to);

        return new TransferDto(transfer);
    }

    @Override
    public TransferDto getTransactionInfo(Long transferId, UUID sessionId) {

        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new SessionNotFoundException(ExceptionTexts.SESSION_NOT_FOUND));
        if (!sessionService.checkSession(session)) {
            throw new UnauthorizedException(ExceptionTexts.SESSION_EXPIRED);
        }

        Transfer transfer = transferRepository.findById(transferId)
                .orElseThrow(() -> new TransferNotFoundException(ExceptionTexts.TRANSFER_NOT_FOUND));

        return new TransferDto(transfer);
    }
}
