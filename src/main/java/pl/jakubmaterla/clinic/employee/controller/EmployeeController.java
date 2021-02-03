package pl.jakubmaterla.clinic.employee.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.jakubmaterla.clinic.employee.model.Employee;
import pl.jakubmaterla.clinic.employee.model.Group;
import pl.jakubmaterla.clinic.employee.model.Position;
import pl.jakubmaterla.clinic.employee.services.EmployeeService;
import pl.jakubmaterla.clinic.employee.services.GroupService;
import pl.jakubmaterla.clinic.employee.services.PositionService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {
    private final EmployeeService service;
    private final GroupService groupService;
    private final PositionService positionService;

    public EmployeeController(EmployeeService service, GroupService groupService, PositionService positionService) {
        this.service = service;
        this.groupService = groupService;
        this.positionService = positionService;
    }

    @RequestMapping(value = "/employees", produces = MediaType.TEXT_HTML_VALUE,method = RequestMethod.GET)
    String readAll(Model model) {
        List<Employee> employees = service.readAll();
        model.addAttribute("employees", employees);
        return "employee/employee";
    }

    @RequestMapping(value = "/employee/{id}", produces = MediaType.TEXT_HTML_VALUE, method = RequestMethod.POST)
    String readEmployeeById(@PathVariable int id, Model model){
        Optional<Employee> empl = service.findById(id);
        model.addAttribute("employee", empl);
        return "employee/employee";
    }

    @RequestMapping (value = "/groups", produces = MediaType.TEXT_HTML_VALUE, method = RequestMethod.GET)
    String readAllGroups(Model model) {
        List<Group> groups = groupService.findAll();
        model.addAttribute("groups", groups);
        return "employee/employee";
    }

    @RequestMapping(value = "/group", produces = MediaType.TEXT_HTML_VALUE, method = RequestMethod.GET)
    String createGroup(Model model) {
        List<Group> groups = groupService.findAll();
        model.addAttribute("groups", groups);
        model.addAttribute("Group", new Group());
        return "employee/newGroup";
    }
    @RequestMapping(value = "/employees", produces = MediaType.TEXT_HTML_VALUE, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, method = RequestMethod.POST)
    public String saveNewGroup(@Valid Group group, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "employee/newGroup";
        }
        else {
            groupService.save(group);
            return "redirect:/groups";
        }
    }

    @RequestMapping(value = "/new-position", produces = MediaType.TEXT_HTML_VALUE, method = RequestMethod.GET)
    String createPosition(Model model) {
        List<Position> positionList = positionService.findAll();
        model.addAttribute("positions", positionList);
        model.addAttribute("Position", new Position());
        return "employee/newPosition";
    }

    @RequestMapping(value = "/save-position", produces = MediaType.TEXT_HTML_VALUE, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, method = RequestMethod.POST)
    public String saveNewPosition(@Valid Position position, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "employee/newPosition";
        }
        else {
            positionService.save(position);
            return "redirect:/groups";
        }
    }

    @RequestMapping(value = "/new-employee", produces = MediaType.TEXT_HTML_VALUE, method = RequestMethod.GET)
    String createEmployee(Model model) {
        model.addAttribute("Employee", new Employee());
        List<Group> groups = groupService.findAll();
        model.addAttribute("groups", groups);
        List<Position> positionList = positionService.findAll();
        model.addAttribute("positions", positionList);
        return "employee/newEmployee";
    }

    @RequestMapping(value = "/save-employee", method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String saveNewEmployee(@Valid Employee employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "employee/newEmployee";
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
