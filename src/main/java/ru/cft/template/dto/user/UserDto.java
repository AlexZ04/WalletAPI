package ru.cft.template.dto.user;

import lombok.Builder;
import lombok.Data;
import ru.cft.template.model.User;

import java.time.LocalDateTime;

@Data
@Builder
public class UserDto {
    private String lastName;
    private String firstName;
    private String middleName;
    private String phone;
    private String email;
    private LocalDateTime birthday;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
