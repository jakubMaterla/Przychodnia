package pl.jakubmaterla.clinic.employee.services;

import org.springframework.stereotype.Service;
import pl.jakubmaterla.clinic.employee.model.Group;
import pl.jakubmaterla.clinic.employee.repositories.GroupRepository;

import java.util.List;

@Service
public class GroupService {
    private final GroupRepository repository;

    public GroupService(GroupRepository repository) {
        this.repository = repository;
    }

    public Group save(Group toCreate) {
        return repository.save(toCreate);
    }

    public List<Group> findAll() {
        return repository.findAll();
    }
}
