package ru.cft.template.dto;

import lombok.Data;

@Data
public class IdResponseDto {
    public Long id;

    public IdResponseDto(Long id) {
        this.id = id;
    }
}
