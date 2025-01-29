package ru.cft.template.dto;

import lombok.Data;

@Data
public class IdResponseDto {
    public int id;

    public IdResponseDto(int id) {
        this.id = id;
    }
}
