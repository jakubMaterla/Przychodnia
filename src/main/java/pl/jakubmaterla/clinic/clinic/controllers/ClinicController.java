package pl.jakubmaterla.clinic.clinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClinicController {

    @GetMapping("/home")
    String home() {
        return "wizytówka/home2";
    }

    @GetMapping("/main")
    String homePage() {
        return "main";
    }

    @GetMapping("homeadmin")
    String homeAdmin(){ return "admin/employee/employees";}
}
