package ru.cft.template.dto.transfer;

import lombok.Data;

@Data
public class TransferCreateByPhoneDto {
    private String phone;
    private Long amount;

    public TransferCreateByPhoneDto(String phone, Long amount) {
        this.phone = phone;
        this.amount = amount;
    }
}
