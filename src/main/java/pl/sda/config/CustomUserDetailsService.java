package pl.sda.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.sda.entity.PersonEntity;
import pl.sda.repository.PersonRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final PersonRepository personRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PersonEntity personEntity = personRepository.findByPeselOrId(username, Long.valueOf(username))
                .orElseThrow(() -> new UsernameNotFoundException("user " + username + " not found"));

        String[] authorities = personEntity.getAuthorities()
                .stream()
                .map(x -> x.getAuthorityName())
                .toArray(String[]::new);

        return User.builder().username(personEntity.getPesel())
                .password(personEntity.getPassword())
                .authorities(authorities)
                .build();
    }

}
