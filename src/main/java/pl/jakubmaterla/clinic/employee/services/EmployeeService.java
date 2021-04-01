package pl.jakubmaterla.clinic.employee.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.jakubmaterla.clinic.employee.model.Employee;
import pl.jakubmaterla.clinic.employee.repositories.EmployeeRepository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;
    private final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> readAll() {
        return repository.findAll();
    }

    public Employee save(Employee employee){
        return repository.save(employee);
    }

    public Optional<Employee> findById(int id) {
        return repository.findById(id);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

   /* @PostConstruct
    public void runAtStart(){
        List<Employee> allUnsorted = repository.findAll();

        Page<Employee> page = repository.findAll(new PageRequest.of(1,3));
        logger.info("TOTAL ELEMENTS: " + page.getTotalElements());
        logger.info("Total pages: " + page.getTotalPages());
        logger.info("ELEMENTS ON PAGE: ");
        printAll(page.getContent());

    }*/

    private void printAll(List<Employee> allUnsorted) {
        allUnsorted.forEach(employee -> logger.info(String.valueOf(employee.toString())));
    }

    public Page<Employee> readPage(int currentPage){
        Pageable pageable = PageRequest.of(currentPage-1, 3);
        return repository.findAll(pageable);
    }
}
