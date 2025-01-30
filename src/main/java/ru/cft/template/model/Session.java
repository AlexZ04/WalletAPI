package ru.cft.template.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "sessions")
public class Session {
    @Id
    UUID sessionId = UUID.randomUUID();
    @ManyToOne(cascade = CascadeType.ALL)
    User user;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime expirationTime;
    Boolean active;

    public Session() {}

    public Session(User user) {
        this.user = user;
        this.expirationTime = LocalDateTime.now().plusMinutes(30);
        this.active = true;
    }
}
