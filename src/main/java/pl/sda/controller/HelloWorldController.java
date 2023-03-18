package pl.sda.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class HelloWorldController {

    //localhost:8080/hello?name=patryk

    @RequestMapping(path = {"/", "/hello", "/powitanie/{imie}"}, method = RequestMethod.GET)
    public String helloWorld(
            @RequestParam(required = false, value = "name") String name,
            @PathVariable(required = false, value = "imie") String nameFromPath,
            Model model) {
        String welcomeMessage = "Hello world!";
        if (name == null && nameFromPath == null) {
            welcomeMessage = "not hello";
        } else if (nameFromPath != null) {
            welcomeMessage = "hello " + name + "!";
        }
        log.info(nameFromPath);
        log.info(name);
        model.addAttribute("welcomeMessage", welcomeMessage);
        return "index";
    }

    //1. dodanie parametru na sciezce - zamiast Hello world - hello - nazwa imienia

}
