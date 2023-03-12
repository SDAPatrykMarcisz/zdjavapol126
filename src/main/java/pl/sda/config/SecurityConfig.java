package pl.sda.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf() //wylaczenie tokenow csrf
                .disable()
                .httpBasic() //wlaczenie logowania przez http basic.and()
                .and()
                .formLogin()
                .disable()//wylaczenie formularza logowania
                .authorizeRequests() //ustalenie ograniczen do poszczegolnych stron
                .antMatchers(HttpMethod.GET, "/persons*/**").authenticated()
                .anyRequest().denyAll(); //dla kazdej niezdefiniowanej wyzej reguly, domyslnie odrzucaj dostep
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //spring.security.user.password=12345
}
