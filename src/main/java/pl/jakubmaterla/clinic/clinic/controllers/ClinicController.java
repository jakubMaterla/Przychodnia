package pl.jakubmaterla.clinic.clinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClinicController {

    @GetMapping("/home")
    String home() {
        return "home";
    }

    @GetMapping("/homepage")
    String homePage() {
        return "wizytówka/main";
    }
}
