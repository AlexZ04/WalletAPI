package ru.cft.template.model;

import jakarta.persistence.*;
import lombok.Data;
import ru.cft.template.model.contstant.Constant;

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

    public Wallet(User user, Long balance) {
        this.user = user;
        this.balance = balance;
    }
}
