package pl.sda.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.sda.entity.PersonEntity;
import pl.sda.repository.PersonRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PersonEntity personEntity = personRepository.findByPesel(username)
                .orElseThrow(() -> new UsernameNotFoundException("user " + username + " not found"));

        return User.builder().username(personEntity.getPesel())
                .password(personEntity.getPassword())
                .roles()
                .build();
    }

}
