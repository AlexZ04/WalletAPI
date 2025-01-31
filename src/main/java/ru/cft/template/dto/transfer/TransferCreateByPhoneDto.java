package ru.cft.template.dto.transfer;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class TransferCreateByPhoneDto {
    @Pattern(regexp = "7\\d{10}")
    private String phone;
    @Min(value = 1)
    private Long amount;

    public TransferCreateByPhoneDto(String phone, Long amount) {
        this.phone = phone;
        this.amount = amount;
    }
}
