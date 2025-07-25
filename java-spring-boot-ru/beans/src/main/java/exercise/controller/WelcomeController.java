package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import exercise.daytime.Daytime;

@RestController
public class WelcomeController {

    @Autowired
    private Daytime daytime;

    @GetMapping("/welcome")
    public String welcome() {
        return "It is " + daytime.getName() + " now! Welcome to Spring!";
    }
}
