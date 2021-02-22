package pl.jakubmaterla.clinic.employee.services;

import org.springframework.stereotype.Service;
import pl.jakubmaterla.clinic.employee.model.Employee;
import pl.jakubmaterla.clinic.employee.repositories.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;

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
}
