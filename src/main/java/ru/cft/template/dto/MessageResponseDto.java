package ru.cft.template.dto;

import lombok.Data;

@Data
public class MessageResponseDto {
    private String message;

    public MessageResponseDto(String message) {
        this.message = message;
    }
}
