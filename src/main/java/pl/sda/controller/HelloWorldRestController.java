package pl.sda.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@Profile("rest")
@RequestMapping(path = "/api")
public class HelloWorldRestController {

    //localhost:8080/hello?name=patryk

    //@GetMapping, @PostMapping, @PutMapping, @DeleteMapping
    @RequestMapping(path = {"/", "/hello", "/powitanie/{imie}"}, method = RequestMethod.GET)
    public ResponseEntity<String> helloWorld(@RequestParam(required = false, value = "name") String name,
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
    }

    //1. dodanie parametru na sciezce - zamiast Hello world - hello - nazwa imienia

}
