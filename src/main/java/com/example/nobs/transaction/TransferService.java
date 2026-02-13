package com.example.nobs.transaction;

import com.example.nobs.cqrs.Command;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class TransferService implements Command<TransferDTO, String> {

    private final BankAccountRepository bankAccountRepository;

    public TransferService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public String execute(TransferDTO transfer) {
        Optional<BankAccount> fromAccount = bankAccountRepository.findById(transfer.getFromUser());
        Optional<BankAccount> toAccount = bankAccountRepository.findById(transfer.getToUser());

        if(fromAccount.isEmpty() || toAccount.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        BankAccount from = fromAccount.get();
        BankAccount to = toAccount.get();

        add(to, transfer.getAmount());

        System.out.println("After adding, before deducting:");
        System.out.println(bankAccountRepository.findById(to.getName()));

        deduct(from, transfer.getAmount());

        return "Success";
    }

    private void deduct(BankAccount bankAccount, Double amount) {
        if (bankAccount.getBalance() < amount) {
            throw new RuntimeException("Not enough money");
        }
        bankAccount.setBalance(bankAccount.getBalance() - amount);
    }

    private void add(BankAccount bankAccount, Double amount) {
        bankAccount.setBalance(bankAccount.getBalance() + amount);
    }
}
