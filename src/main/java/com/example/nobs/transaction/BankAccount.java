package com.example.nobs.transaction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "bank")
@Data
public class BankAccount {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "balance")
    private Double balance;
}
