package pl.jakubmaterla.clinic.clinic.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.jakubmaterla.clinic.contact.model.Mailer;
import pl.jakubmaterla.clinic.employee.model.Employee;
import pl.jakubmaterla.clinic.employee.services.EmployeeService;

import java.util.List;
import java.util.Optional;

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

    @GetMapping(value = "/team/{currentPage}")
    String readAll(Model model, @PathVariable int currentPage) {
        Page<Employee> page = service.readPage(currentPage);
        int totalPages = page.getTotalPages();

        List<Employee> employees = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("employees", employees);
        return "guest/team";
    }

    @GetMapping("/team/one-of-team/{id}")
    public String oneOfTeam(@PathVariable int id, Model model) {
        Optional<Employee> emp = service.findById(id);
        model.addAttribute("emp", emp);
        return "guest/oneOfTeam";
    }

    @GetMapping("/news")
    public String news() {
        return "guest/news";
    }

    @GetMapping("/cases")
    public String cases(){
        return "guest/cases";
    }

    @GetMapping("/job")
    String job() {
        return "guest/job";
    }

    @GetMapping("/job/more")
    public String jobMore(){
        return "guest/jobMore";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("mailer", new Mailer());
        return "guest/contact";
    }


    // admin
    @GetMapping("/employee")
    String employee(){ return "admin/employee/employees";}

    @GetMapping("/index")
    String index() {
        return "admin/index";
    }
}
