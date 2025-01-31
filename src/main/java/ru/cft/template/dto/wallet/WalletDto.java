package ru.cft.template.dto.wallet;

import lombok.Builder;
import lombok.Data;
import ru.cft.template.model.Wallet;

@Data
@Builder
public class WalletDto {
    private Long walletNumber;
    private Long balance;
}
