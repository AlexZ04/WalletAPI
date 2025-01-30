package ru.cft.template.dto.wallet;

import lombok.Data;
import ru.cft.template.model.Wallet;

@Data
public class WalletDto {
    private Long walletNumber;
    private Long balance;

    public WalletDto(Wallet wallet) {
        walletNumber = wallet.getId();
        balance = wallet.getBalance();
    }
}
