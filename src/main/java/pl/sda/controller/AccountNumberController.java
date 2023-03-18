package pl.sda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.repository.AccountRepository;

@RestController
public class AccountNumberController {

    @Autowired
    private AccountRepository accountRepository;

    @DeleteMapping
    public void removeByAccountNumber(String pesel, String accountNumber){
        accountRepository.findByPerson_PeselAndAccountNumber(pesel,accountNumber)
                .ifPresent(accountEntity -> accountRepository.delete(accountEntity));
    }
}
