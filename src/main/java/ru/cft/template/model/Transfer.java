package ru.cft.template.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "transfers")
public class Transfer {
    @Id
    @GeneratedValue
    private Long transferId;
    @ManyToOne
    private Wallet fromWallet;
    @ManyToOne
    private Wallet toWallet;
    private Long amount;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime creationTime;

    public Transfer() {
    }

    public Transfer(Wallet from, Wallet to, Long amount, LocalDateTime creationTime) {
        fromWallet = from;
        toWallet = to;
        this.amount = amount;
        this.creationTime = creationTime;
    }
}
