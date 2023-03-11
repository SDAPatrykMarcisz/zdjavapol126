package pl.sda.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.sda.entity.PersonEntity;
import pl.sda.service.PersonService;

import java.util.List;

@RestController
@RequestMapping(path = "/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

//    public PersonController(PersonService personService) {
//        this.personService = personService;
//    }

    @PostMapping
    public void createPerson(
            @RequestBody PersonEntity requestBody){

        personService.savePerson(requestBody);
    }

    public void getPersonByPesel(){

    }

    @GetMapping
    public List<PersonEntity> getAllPersons(){
        return personService.getAllPersons();
    }

    public void updatePersonByPesel(){

    }

    public void deletePersonByPesel(){

    }

}
