package pl.sda.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import pl.sda.entity.PersonEntity;
import pl.sda.service.PersonService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/persons")
@RequiredArgsConstructor
public class PersonController {

    @Value("${spring.security.user.password}")
    private String defaultUserPassword;
    private final PersonService personService;

//    public PersonController(PersonService personService) {
//        this.personService = personService;
//    }

    @PostMapping
    public void createPerson(@RequestBody @Valid PersonEntity requestBody){

        personService.savePerson(requestBody);
    }

    @GetMapping(path = "/{pesel}")
    public PersonEntity getPersonByPesel(@PathVariable String pesel){
        return personService.getByPesel(pesel);
    }

    @GetMapping()
    public List<PersonEntity> getAllPersons(){
        return personService.getAllPersons();
    }

    @PutMapping(path = "/{pesel}")
    public void updatePersonByPesel(
            @RequestBody PersonEntity requestBody,
            @PathVariable String pesel){
        personService.updatePersonByPesel(pesel, requestBody);
    }

    //persons/12322112012 --> pesel=12322112012
    @DeleteMapping(path = "/{pesel}")
    public void deletePersonByPesel(
            @PathVariable String pesel){
        personService.deletePerson(pesel);
    }

}
