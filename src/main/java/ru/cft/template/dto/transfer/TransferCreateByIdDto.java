package ru.cft.template.dto.transfer;

import lombok.Data;

@Data
public class TransferCreateByIdDto {
    private Long walletId;
    private Long amount;

    public TransferCreateByIdDto(Long id, Long amount) {
        walletId = id;
        this.amount = amount;
    }
}
