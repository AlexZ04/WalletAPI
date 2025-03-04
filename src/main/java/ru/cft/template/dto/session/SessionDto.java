package ru.cft.template.dto.session;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class SessionDto {
    private UUID sessionId;
    private LocalDateTime expirationTime;
    private Boolean active;
}
