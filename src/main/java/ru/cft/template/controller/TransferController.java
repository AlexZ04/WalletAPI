package ru.cft.template.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.cft.template.dto.transfer.TransferDto;
import ru.cft.template.model.Wallet;
import ru.cft.template.service.TransferService;

@RestController
@RequestMapping("api/Transfer")
@AllArgsConstructor
public class TransferController {
    private final TransferService transferService;

    @PostMapping("/transfers/byId")
    public TransferDto createTransactionByWalletId() {
        return null;
    }

    @PostMapping("/transfers/byPhone")
    public TransferDto createTransactionByPhone() {
        return null;
    }
}
