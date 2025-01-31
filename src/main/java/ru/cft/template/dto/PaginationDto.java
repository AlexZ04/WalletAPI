package ru.cft.template.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaginationDto {
    private int size;
    private int count;
    private int current;

    public PaginationDto(int size, int count, int current) {
        this.size = size;
        this.count = count;
        this.current = current;
    }
}
