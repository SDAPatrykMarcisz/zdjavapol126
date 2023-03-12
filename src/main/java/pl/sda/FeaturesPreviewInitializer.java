package pl.sda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.sda.entity.PersonEntity;
import pl.sda.repository.AccountRepository;
import pl.sda.repository.PersonRepository;
import pl.sda.service.MoneyTransferExecutor;

import java.math.BigDecimal;
import java.util.List;

@Component
public class FeaturesPreviewInitializer implements CommandLineRunner {
    @Autowired
    private MoneyTransferExecutor moneyTransferExecutor;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private List<String> myFilesList;

    @Autowired
    private List<List<String>> myFilesLists;

    @Override
    public void run(String... args) throws Exception {
        List<PersonEntity> persons = personRepository.findAll();
        System.out.println(persons);

        PersonEntity personWithPesel = personRepository.findByPesel("51101069339").orElseThrow();
        System.out.println(personWithPesel);

        System.out.println(accountRepository.findAll());

        try {
            moneyTransferExecutor.send(1000L, 2000L, BigDecimal.valueOf(500));
        } catch (Exception e){

        }

        System.out.println(accountRepository.findAll());

        System.out.println(myFilesList);

        System.out.println(myFilesLists);

    }
}
