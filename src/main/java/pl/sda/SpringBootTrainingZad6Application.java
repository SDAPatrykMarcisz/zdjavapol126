package pl.sda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.sda.entity.PersonEntity;
import pl.sda.repository.AccountRepository;
import pl.sda.repository.PersonRepository;
import pl.sda.service.MoneyTransferExecutor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class SpringBootTrainingZad6Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTrainingZad6Application.class, args);
    }

}
