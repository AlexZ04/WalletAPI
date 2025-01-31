package ru.cft.template.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cft.template.exception.ExceptionTexts;
import ru.cft.template.exception.SessionNotFoundException;
import ru.cft.template.exception.UnauthorizedException;
import ru.cft.template.model.Session;
import ru.cft.template.repository.SessionRepository;
import ru.cft.template.service.SecurityService;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SecurityServiceImpl implements SecurityService {
    private final SessionRepository sessionRepository;

    @Override
    public Session getSession(UUID sessionId) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new SessionNotFoundException(ExceptionTexts.SESSION_NOT_FOUND));

        checkSession(session);

        return session;
    }

    void checkSession(Session session) {
        if (session.getExpirationTime().isBefore(LocalDateTime.now())) {
            session.setActive(false);
            sessionRepository.save(session);

            throw new UnauthorizedException(ExceptionTexts.SESSION_EXPIRED);
        }
    }
}
