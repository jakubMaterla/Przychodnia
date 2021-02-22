package pl.jakubmaterla.clinic.clinic.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.jakubmaterla.clinic.employee.model.Employee;
import pl.jakubmaterla.clinic.employee.services.EmployeeService;

import java.util.List;

@Controller
public class ClinicController {
    private final EmployeeService service;

    public ClinicController(EmployeeService service) {
        this.service = service;
    }

    // guest
    @GetMapping("/home")
    String home() {
        return "guest/home";
    }

    @GetMapping(value = "/team")
    String readAll(Model model) {
        List<Employee> employees = service.readAll();
        model.addAttribute("employees", employees);
        return "guest/team";
    }

    @GetMapping("/team/one-of-team/{id}")
    public String oneOfTeam(@PathVariable int id, Model model) {
        model.addAttribute("teammate", service.findById(id));
        return "guest/oneOfTeam";
    }

    @GetMapping("/news")
    public String news() {
        return "guest/news";
    }

    @GetMapping("/job")
    String homePage() {
        return "guest/job";
    }


    // admin
    @GetMapping("/employee")
    String homeAdmin(){ return "admin/employee/employees";}

    @GetMapping("/index")
    String index() {
        return "admin/index";
    }
}
