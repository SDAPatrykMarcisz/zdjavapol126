package pl.sda.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    @Primary
    public List<String> myFileslist(){
        return List.of("a", "b", "c", "d");
    }

    @Bean
    @Qualifier("myList")
    public List<String> myFileslist2(){
        return List.of("d", "e", "f", "g");
    }

}
