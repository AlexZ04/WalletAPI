package ru.cft.template.dto;

import lombok.Data;

@Data
public class ResponseDto {
    private int code;
    private String message;

    public ResponseDto(int value, String message) {
        this.code = value;
        this.message = message;
    }
}
