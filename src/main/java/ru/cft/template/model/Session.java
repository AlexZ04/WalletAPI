package ru.cft.template.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import ru.cft.template.model.contstant.Constant;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "sessions")
public class Session {
    @Id
    private UUID sessionId = UUID.randomUUID();
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime expirationTime;
    private Boolean active;

    public Session() {}

    public Session(User user, LocalDateTime expTime, Boolean isActive) {
        this.user = user;
        expirationTime = LocalDateTime.now().plusMinutes(Constant.SESSION_LIFETIME_IN_MINUTES);
        active = true;
    }
}
