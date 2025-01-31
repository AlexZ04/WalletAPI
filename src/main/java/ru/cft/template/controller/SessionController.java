package ru.cft.template.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.cft.template.dto.session.LoginDto;
import ru.cft.template.dto.session.SessionDto;
import ru.cft.template.service.SessionService;

import java.util.UUID;

@RestController
@RequestMapping("api")
@AllArgsConstructor
public class SessionController {
    private final SessionService service;

    @PostMapping("/sessions")
    public SessionDto login(@RequestBody LoginDto loginData) {
        return service.login(loginData);
    }

    @GetMapping("/sessions/{sessionId}")
    public SessionDto getSessionInfo(@PathVariable UUID sessionId) {
        return service.getSessionInfo(sessionId);
    }

    @DeleteMapping("/sessions/{sessionId}")
    public void logout(@PathVariable UUID sessionId) {
        service.deleteSession(sessionId);
    }
}
