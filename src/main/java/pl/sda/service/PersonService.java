package pl.sda.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.entity.PersonEntity;
import pl.sda.repository.PersonRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    public List<PersonEntity> getAllPersons(){
        return personRepository.findAll();
    }

    public void savePerson(PersonEntity personEntity){
        personEntity.setPassword(passwordEncoder.encode(personEntity.getPassword()));
        personRepository.save(personEntity);
    }

    public void deletePerson(String pesel) {
       //pobranie i usuniecie
        personRepository.findByPesel(pesel)
                //znajdujemy obiekt PersonEntity
                // po peselu, i zwracamy go jako Optional
                .ifPresent(personEntity -> personRepository.delete(personEntity));

       //wykonanie query usuwajacego
        personRepository.deleteByPesel(pesel);
    }

    public PersonEntity getByPesel(String pesel) {
        return personRepository.findByPesel(pesel)
                .orElseThrow();
    }

//    @Transactional
    public void updatePersonByPesel(String pesel, PersonEntity updatedData) {
        personRepository.findByPesel(pesel)
                .map(objectInsideOptional -> {
                    objectInsideOptional.setFirstName(updatedData.getFirstName());
                    objectInsideOptional.setLastName(updatedData.getLastName());
                    return objectInsideOptional;
                })
                .ifPresent(updatedEntity -> personRepository.save((updatedEntity)));
    }
}
