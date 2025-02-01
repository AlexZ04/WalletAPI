package ru.cft.template.dto.transfer;

import lombok.Builder;
import lombok.Data;
import ru.cft.template.dto.PaginationDto;

import java.util.List;

@Data
@Builder
public class TransferPageDto {
    private List<TransferDto> transfers;
    private PaginationDto pagination;
}
