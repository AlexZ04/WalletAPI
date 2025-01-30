package ru.cft.template.service;

import ru.cft.template.dto.session.LoginDto;
import ru.cft.template.dto.session.SessionDto;

public interface SessionService {
    SessionDto login(LoginDto loginData);
}
