package ru.cft.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cft.template.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByPhone(String phone);

    Boolean existsByEmail(String email);
}
