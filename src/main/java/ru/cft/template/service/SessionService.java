package ru.cft.template.service;

import ru.cft.template.dto.session.LoginDto;
import ru.cft.template.dto.session.SessionDto;

import java.util.UUID;

public interface SessionService {
    SessionDto login(LoginDto loginData);
    SessionDto getSessionInfo(UUID sessionId);
    void deleteSession(UUID sessionId);
    void extendSession(UUID sessionId);
}
