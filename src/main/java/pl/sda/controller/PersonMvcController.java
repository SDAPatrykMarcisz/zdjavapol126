package pl.sda.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.entity.PersonEntity;
import pl.sda.service.PersonService;

@Controller
@Slf4j
@RequestMapping(path = "/dane-klienta")
@RequiredArgsConstructor
public class PersonMvcController {

    private final PersonService personService;

    @RequestMapping(path = "/{pesel}", method = RequestMethod.GET)
    public String getPerson(
            @PathVariable(value = "pesel") String pesel,
            Model model) {
        model.addAttribute("person", personService.getByPesel(pesel));

        return "index";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getPersons(Model model) {
        model.addAttribute("persons",
                personService.getAllPersons());
        return "person-list";
    }

    @GetMapping("/nowy-uzytkownik")
    public String newPersonPage(Model model){
        model.addAttribute("formObj", new PersonEntity());
        return "create-new-user";
    }

    @PostMapping("/nowy-uzytkonik")
    public String saveNewPerson(@ModelAttribute(name = "formObj") PersonEntity entity){
        log.info(entity.toString());
        return "redirect:/dane-klienta";
    }
}
