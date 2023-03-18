package pl.sda.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class HelloWorldController {

    //localhost:8080/hello?name=patryk

    @RequestMapping(path = {"/", "/hello", "/powitanie/{imie}"}, method = RequestMethod.GET)
    public String helloWorld(@RequestParam(required = false, value = "name") String name,
                                     @PathVariable(required = false, value = "imie") String nameFromPath){
        if(name == null && nameFromPath == null){
            name = "World";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not hello");
        } else if(nameFromPath != null){
            name = nameFromPath;
        }
        log.info(nameFromPath);
        log.info(name);
        String welcomeMessage = "hello " + name + "!";
        return ResponseEntity.ok(welcomeMessage);
        return "index";
    }

    //1. dodanie parametru na sciezce - zamiast Hello world - hello - nazwa imienia

}
