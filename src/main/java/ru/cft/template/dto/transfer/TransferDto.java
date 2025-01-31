package ru.cft.template.dto.transfer;

import lombok.Data;
import ru.cft.template.model.Transfer;

import java.time.LocalDateTime;

@Data
public class TransferDto {
    private Long transferId;
    private LocalDateTime creationTime;
    private Long amount;

    public TransferDto(Transfer transfer) {
        transferId = transfer.getTransferId();
        creationTime = transfer.getCreationTime();
        amount = transfer.getAmount();
    }
}
