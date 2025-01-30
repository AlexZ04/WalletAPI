package ru.cft.template.dto.session;

import lombok.Data;

@Data
public class LoginDto {
    private Long userId;
    private String password;

    public LoginDto(Long id, String password) {
        this.userId = id;
        this.password = password;
    }
}
