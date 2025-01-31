package ru.cft.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.cft.template.model.User;
import ru.cft.template.model.Wallet;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Wallet findByUser(User user);
    @Query("SELECT w from Wallet w WHERE w.user.phone = :phone")
    Optional<Wallet> findByUserPhone(@Param("phone") String phone);
}
