package ru.cft.template.dto.transfer;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class TransferCreateByIdDto {
    private Long walletId;
    @Min(value = 1)
    private Long amount;

    public TransferCreateByIdDto(Long id, Long amount) {
        walletId = id;
        this.amount = amount;
    }
}
