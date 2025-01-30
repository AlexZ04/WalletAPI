package ru.cft.template.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.cft.template.dto.session.LoginDto;
import ru.cft.template.dto.session.SessionDto;
import ru.cft.template.service.SessionService;

@RestController
@RequestMapping("api/Session")
@AllArgsConstructor
public class SessionController {
    private final SessionService service;

    @PostMapping("/sessions")
    public SessionDto login(@RequestBody LoginDto loginData) {
        return service.login(loginData);
    }
}
