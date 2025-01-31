package ru.cft.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.cft.template.model.Transfer;
import ru.cft.template.model.Wallet;

public interface TransferRepository extends JpaRepository<Transfer, Long> {

}
