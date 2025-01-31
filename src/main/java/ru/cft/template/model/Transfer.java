package ru.cft.template.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "transfers")
public class Transfer {
    @Id
    private Long transferId;
    @ManyToOne
    private Wallet fromWallet;
    @ManyToOne
    private Wallet toWallet;
    private Long amount;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime creationTime;

    public Transfer() {}

    public Transfer(Wallet from, Wallet to, Long amount) {
        fromWallet = from;
        toWallet = to;
        this.amount = amount;
    }
}
