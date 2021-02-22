package pl.jakubmaterla.clinic.employee.services;

import org.springframework.stereotype.Service;
import pl.jakubmaterla.clinic.employee.model.Position;
import pl.jakubmaterla.clinic.employee.repositories.PositionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PositionService {
    private final PositionRepository repository;

    public PositionService(PositionRepository repository) {
        this.repository = repository;
    }

    public void save(Position position) {
        repository.save(position);
    }

    public List<Position> findAll() {
        return repository.findAll();
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public Optional<Position> findById(Integer id) {
        return repository.findById(id);
    }
}
