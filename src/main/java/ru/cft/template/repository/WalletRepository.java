package ru.cft.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cft.template.model.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
