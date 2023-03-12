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
                .logout().and()
                .headers().frameOptions().sameOrigin().and()
                .authorizeRequests() //ustalenie ograniczen do poszczegolnych stron
                .antMatchers(HttpMethod.POST, "/persons*/**").hasAnyRole("CREATOR", "ADMIN")
                //.antMatchers(HttpMethod.POST, "/persons*/**").hasAuthority("ROLE_CREATOR")
                .antMatchers(HttpMethod.GET, "/persons*/**").hasAnyAuthority("ROLE_READER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/persons*/**").hasAnyRole("UPDATER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/persons*/**").hasAnyRole("REMOVER", "ADMIN")
                .antMatchers("/h2-console**/**").permitAll()
                .anyRequest().denyAll(); //dla kazdej niezdefiniowanej wyzej reguly, domyslnie odrzucaj dostep
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //spring.security.user.password=12345
}
