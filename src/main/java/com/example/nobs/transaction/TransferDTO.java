package com.example.nobs.transaction;

import lombok.Data;

@Data
public class TransferDTO {

    private String fromUser;
    private String toUser;
    private Double amount;
}
