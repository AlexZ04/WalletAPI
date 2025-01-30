package ru.cft.template.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cft.template.configuration.Security;
import ru.cft.template.dto.session.LoginDto;
import ru.cft.template.dto.session.SessionDto;
import ru.cft.template.exception.SessionNotFoundException;
import ru.cft.template.exception.UnauthorizedException;
import ru.cft.template.exception.UserNotFoundException;
import ru.cft.template.model.Session;
import ru.cft.template.model.User;
import ru.cft.template.repository.SessionRepository;
import ru.cft.template.repository.UserRepository;
import ru.cft.template.service.SessionService;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SessionServiceImpl implements SessionService {
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;

    @Override
    public SessionDto login(LoginDto loginData) {
        User user = userRepository.findById(loginData.getUserId())
                .orElseThrow(() -> new UnauthorizedException("Incorrect credentials"));

        if (!Security.checkPassword(loginData.getPassword(), user.getPassword())) {
            throw new UnauthorizedException("Incorrect credentials");
        }

        Session session = new Session(user);
        sessionRepository.save(session);

        return new SessionDto(session);
    }

    @Override
    public SessionDto getSessionInfo(UUID sessionId) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new SessionNotFoundException("Session not found"));

        return new SessionDto(session);
    }

    @Override
    public void deleteSession(UUID sessionId) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new SessionNotFoundException("Session not found"));

        session.setActive(false);
        sessionRepository.save(session);
    }

    @Override
    public void extendSession(UUID sessionId) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new SessionNotFoundException("Session not found"));

        session.setActive(true);
        session.setExpirationTime(LocalDateTime.now().plusMinutes(30));
        sessionRepository.save(session);
    }

    @Override
    public boolean checkSession(Session session) {
        if (session.getExpirationTime().isBefore(LocalDateTime.now())) {
            session.setActive(false);
            sessionRepository.save(session);
        }

        return session.getActive();
    }
}
