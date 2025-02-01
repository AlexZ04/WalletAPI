package ru.cft.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cft.template.model.Transfer;

public interface TransferRepository extends JpaRepository<Transfer, Long> {

}
