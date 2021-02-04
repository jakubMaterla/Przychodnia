package pl.jakubmaterla.clinic.employee.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.jakubmaterla.clinic.employee.model.Employee;
import pl.jakubmaterla.clinic.employee.model.Position;
import pl.jakubmaterla.clinic.employee.services.EmployeeService;
import pl.jakubmaterla.clinic.employee.services.PositionService;

import javax.validation.Valid;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
public class EmployeeController {
    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    private final EmployeeService service;
    private final PositionService positionService;

    public EmployeeController(EmployeeService service, PositionService positionService) {
        this.service = service;
        this.positionService = positionService;
    }

    @RequestMapping(value = "/team", produces = MediaType.TEXT_HTML_VALUE,method = RequestMethod.GET)
    String readAll(Model model) {
        List<Employee> employees = service.readAll();
        model.addAttribute("employees", employees);
        return "wizytówka/team";
    }
    @RequestMapping(value = "/employees", produces = MediaType.TEXT_HTML_VALUE,method = RequestMethod.GET)
    String readAllEmployees(Model model) {
        List<Employee> employees = service.readAll();
        model.addAttribute("employees", employees);
        return "admin/employee/employees";
    }

    /*@RequestMapping(value = "/employee/{id}", produces = MediaType.TEXT_HTML_VALUE, method = RequestMethod.POST)
    String readEmployeeById(@PathVariable int id, Model model){
        Optional<Employee> empl = service.findById(id);
        model.addAttribute("employee", empl);
        return "employee/employee";
    }*/



    @RequestMapping(value = "/new-position", produces = MediaType.TEXT_HTML_VALUE, method = RequestMethod.GET)
    String createPosition(Model model) {
        List<Position> positionList = positionService.findAll();
        model.addAttribute("positions", positionList);
        model.addAttribute("Position", new Position());
        return "admin/employee/newPosition";
    }

    @RequestMapping(value = "/save-position", produces = MediaType.TEXT_HTML_VALUE, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, method = RequestMethod.POST)
    public String saveNewPosition(@Valid Position position, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "admin/employee/newPosition";
        }
        else {
            positionService.save(position);
            return "redirect:/employees";
        }
    }

    @RequestMapping(value = "/new-employee", produces = MediaType.TEXT_HTML_VALUE, method = RequestMethod.GET)
    String createEmployee(Model model) {
        model.addAttribute("Employee", new Employee());
        List<Position> positionList = positionService.findAll();
        model.addAttribute("positions", positionList);
        return "admin/employee/newEmployee";
    }

    @RequestMapping(value = "/save-employee", method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String saveNewEmployee(@Valid Employee employee, @RequestPart(value = "filename") MultipartFile file, BindingResult bindingResult) {
        logger.info(employee.getFilename());
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        logger.info(fileName.toUpperCase(Locale.ROOT));
        if (bindingResult.hasErrors()) {
            return "admin/employee/newEmployee";
        }
        else {
            service.save(employee);
            return "redirect:/employees";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    ResponseEntity<?> readEmployees(){
        return ResponseEntity.ok(service.readAll());
    }

    @ResponseBody
    @RequestMapping(value = "/employees", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> readEmployee(@RequestBody @Valid Employee toCreate) {
        Employee result = service.save(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }


    /*@GetMapping("/employee")
    ResponseEntity<List<Employee>> readAll(){
        return ResponseEntity.ok(service.readAll());
    }*/

    /*@PostMapping("/employee")
    ResponseEntity<?> createEmployee(@RequestBody @Valid Group toCreate){
        Group result = groupService.save(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }*/
}
