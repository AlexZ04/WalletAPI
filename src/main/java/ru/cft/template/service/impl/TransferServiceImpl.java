package ru.cft.template.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cft.template.dto.transfer.TransferDto;
import ru.cft.template.exception.NotEnoughMoneyException;
import ru.cft.template.exception.WalletNotFoundException;
import ru.cft.template.model.Transfer;
import ru.cft.template.model.Wallet;
import ru.cft.template.repository.TransferRepository;
import ru.cft.template.repository.WalletRepository;
import ru.cft.template.service.TransferService;

@Service
@AllArgsConstructor
public class TransferServiceImpl implements TransferService {
    private final TransferRepository transferRepository;
    private final WalletRepository walletRepository;

    @Override
    public Wallet findWalletById(Long id) {
        return walletRepository.findById(id)
                .orElseThrow(() -> new WalletNotFoundException("Can't find the wallet"));
    }

    @Override
    public Wallet findWalletByPhone(String phone) {
        return walletRepository.findByUserPhone(phone)
                .orElseThrow(() -> new WalletNotFoundException("Can't find the wallet"));
    }

    @Override
    public TransferDto createTransfer(Wallet from, Wallet to, Long amount) {

        if (from.getBalance() < amount) {
            throw new NotEnoughMoneyException("Not enough money");
        }

        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);

        Transfer transfer = new Transfer(from, to, amount);
        transferRepository.save(transfer);
        walletRepository.save(from);
        walletRepository.save(to);

        return new TransferDto(transfer);
    }
}
