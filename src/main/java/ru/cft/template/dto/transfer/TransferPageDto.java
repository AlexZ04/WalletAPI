package ru.cft.template.dto.transfer;

import lombok.Builder;
import lombok.Data;
import ru.cft.template.dto.PaginationDto;
import ru.cft.template.model.Transfer;

import java.util.List;

@Data
@Builder
public class TransferPageDto {
    private List<TransferDto> transfers;
    private PaginationDto pagination;

    public TransferPageDto(List<TransferDto> transfers, PaginationDto pagination) {
        this.transfers = transfers;
        this.pagination = pagination;
    }
}
