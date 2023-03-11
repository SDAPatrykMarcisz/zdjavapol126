package pl.sda.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.entity.PersonEntity;
import pl.sda.repository.PersonRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public List<PersonEntity> getAllPersons(){
        return personRepository.findAll();
    }

    public void savePerson(PersonEntity personEntity){
        personRepository.save(personEntity);
    }

}
