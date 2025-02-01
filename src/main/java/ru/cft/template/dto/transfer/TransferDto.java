package ru.cft.template.dto.transfer;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TransferDto {
    private Long transferId;
    private LocalDateTime creationTime;
    private Long amount;
}
