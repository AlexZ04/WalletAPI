package ru.cft.template.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "wallets")
public class Wallet {
    @Id
    @GeneratedValue
    Long id;
    Long balance;
    Double cashback;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime lastOutTransactionTime = null;
    @OneToOne(cascade = CascadeType.ALL)
    User user;

    public Wallet() {
    }

    public Wallet(User user, Long balance, Double cashback) {
        this.user = user;
        this.balance = balance;
        this.cashback = cashback;
    }
}
