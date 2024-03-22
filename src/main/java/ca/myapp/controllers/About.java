package ca.myapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class About {
    @GetMapping("/about")
    public String getName(){
        return "Danieva Paraiso";
    }

}
