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
    public void send(Long accountSenderId, Long accountReceiverId, BigDecimal value) {
        AccountEntity senderAccount = accountRepository.findById(accountSenderId)
                .orElseThrow(() -> new UnsupportedOperationException("Account not exists"));
        AccountEntity receiverAccount = accountRepository.findById(accountReceiverId)
                .orElseThrow(() -> new UnsupportedOperationException("Account not exists"));

        if (senderAccount.getBalance().compareTo(value) < 0) {
            throw new UnsupportedOperationException("Wrong balance");
        }

        BigDecimal senderBalance = senderAccount.getBalance().subtract(value);
        BigDecimal receiverBalance = receiverAccount.getBalance().add(value);

        senderAccount.setBalance(senderBalance);
        receiverAccount.setBalance(receiverBalance);

//        if (true) throw new NullPointerException();
    }
}
