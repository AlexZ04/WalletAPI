package ru.cft.template.service;

import ru.cft.template.model.Session;

import java.util.UUID;

public interface SecurityService {
    Session getSession(UUID sessionId);
}
