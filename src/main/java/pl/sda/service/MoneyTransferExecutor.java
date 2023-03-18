package pl.sda.service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.entity.AccountEntity;
import pl.sda.repository.AccountRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service @RequiredArgsConstructor

public class MoneyTransferExecutor {

    private final AccountRepository accountRepository;

    @Transactional
    public void send(String accountSenderNumber, String accountReceiverNumber, BigDecimal value){
        AccountEntity senderAccount = accountRepository.findByAccountNumber(accountSenderNumber)
                .orElseThrow(() -> new UnsupportedOperationException("Account not exists"));
        AccountEntity receiverAccount = accountRepository.findByAccountNumber(accountReceiverNumber)
                .orElseThrow(() -> new UnsupportedOperationException("Account not exists"));

        send(senderAccount, receiverAccount, value);
    }

    @Transactional
    public void send(Long accountSenderId, Long accountReceiverId, BigDecimal value) {
        AccountEntity senderAccount = accountRepository.findById(accountSenderId)
                .orElseThrow(() -> new UnsupportedOperationException("Account not exists"));
        AccountEntity receiverAccount = accountRepository.findById(accountReceiverId)
                .orElseThrow(() -> new UnsupportedOperationException("Account not exists"));

        send(senderAccount, receiverAccount, value);
    }

    public void send(AccountEntity sender, AccountEntity receiver, BigDecimal value){
        if (sender.getBalance().compareTo(value) < 0) {
            throw new UnsupportedOperationException("Wrong balance");
        }

        BigDecimal senderBalance = sender.getBalance().subtract(value);
        BigDecimal receiverBalance = receiver.getBalance().add(value);

        sender.setBalance(senderBalance);
        receiver.setBalance(receiverBalance);
    }
}
