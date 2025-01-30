package ru.cft.template.dto.session;

import lombok.Data;
import ru.cft.template.model.Session;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class SessionDto {
    private UUID sessionId;
    private LocalDateTime expirationTime;
    private Boolean active;

    public SessionDto(Session session) {
        this.sessionId = session.getSessionId();
        this.expirationTime = session.getExpirationTime();
        this.active = session.getActive();
    }
}
