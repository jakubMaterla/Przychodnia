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
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
public class EmployeeController {
    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    private final EmployeeService service;
    private final PositionService positionService;

    public EmployeeController(EmployeeService service, PositionService positionService) {
        this.service = service;
        this.positionService = positionService;
    }

    @GetMapping("/employees")
    String readAllEmployees(Model model) {
        List<Employee> employees = service.readAll();
        model.addAttribute("employees", employees);
        model.addAttribute("positions", positionService.findAll());
        return "admin/employee/employees";
    }

    @RequestMapping(value = "/employees/addNew", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String createEmployee(@Valid Employee employee, @RequestParam("file") MultipartFile file, BindingResult bindingResult) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (bindingResult.hasErrors()){
            return "redirect:/employees";
        }
        String baseDirectory = "C:\\Users\\lukasz\\IdeaProjects\\Moje Projekty\\clinic\\src\\main\\resources\\static\\img\\" ;
        file.transferTo(new File(baseDirectory + fileName ));
        employee.setFilename(file.getOriginalFilename());
        service.save(employee);
        return "redirect:/employees";
    }

    @GetMapping("/employees/findById/{id}")
    @ResponseBody
    public Optional<Employee> findById(@PathVariable Integer id){
        return service.findById(id);
    }

    @RequestMapping(value = "/employees/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update( Employee employee) {
        /*String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String baseDirectory = "C:\\Users\\lukasz\\IdeaProjects\\Moje Projekty\\clinic\\src\\main\\resources\\static\\img\\" ;
        file.transferTo(new File(baseDirectory + fileName ));
        employee.setFilename(file.getOriginalFilename());*/
        service.save(employee);
        return "redirect:/employees";
    }

    @RequestMapping(value = "/employees/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(Integer id){
        service.deleteById(id);
        return "redirect:/employees";
    }

    /*@RequestMapping(value = "/save-employee", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String saveNewEmployee(@Valid Employee employee, @RequestParam("file") MultipartFile file, BindingResult bindingResult) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (bindingResult.hasErrors()) {
            return "admin/employee/newEmployee";
        }
        else {
            String baseDirectory = "C:\\Users\\lukasz\\IdeaProjects\\Moje Projekty\\clinic\\src\\main\\resources\\static\\images\\" ;
            file.transferTo(new File(baseDirectory + fileName ));
            employee.setFilename(file.getOriginalFilename());
            service.save(employee);
            return "redirect:/employees";
        }
    }*/

    /*@ResponseBody
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
    }*/


    /*@PostMapping("/employee")
    ResponseEntity<?> createEmployee(@RequestBody @Valid Group toCreate){
        Group result = groupService.save(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }*/
}
