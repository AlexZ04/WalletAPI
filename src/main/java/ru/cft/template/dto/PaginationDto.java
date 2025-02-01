package ru.cft.template.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaginationDto {
    private int size;
    private int count;
    private int current;
}
