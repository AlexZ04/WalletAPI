package ru.cft.template.dto.wallet;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WalletDto {
    private Long walletNumber;
    private Long balance;
    private Double cashback;
}
