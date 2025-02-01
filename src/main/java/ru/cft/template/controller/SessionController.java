package ru.cft.template.controller;

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
    private final SessionService sessionService;

    @PostMapping("/sessions")
    public SessionDto login(@RequestBody LoginDto loginData) {
        return sessionService.login(loginData);
    }

    @GetMapping("/sessions/{sessionId}")
    public SessionDto getSessionInfo(@PathVariable UUID sessionId) {
        return sessionService.getSessionInfo(sessionId);
    }

    @DeleteMapping("/sessions/{sessionId}")
    public void logout(@PathVariable UUID sessionId) {
        sessionService.deleteSession(sessionId);
    }
}
