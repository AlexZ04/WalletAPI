package ru.cft.template.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "wallets")
public class Wallet {
    @Id
    @GeneratedValue
    Long id;
    Long balance;
    @OneToOne
    User user;

    public Wallet() {}

    public Wallet(User user) {
        this.user = user;
        balance = 100L;
    }
}
