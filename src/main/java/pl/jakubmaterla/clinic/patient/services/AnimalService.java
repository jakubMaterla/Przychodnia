package pl.jakubmaterla.clinic.patient.services;

import org.springframework.stereotype.Service;
import pl.jakubmaterla.clinic.patient.model.Animal;
import pl.jakubmaterla.clinic.patient.repositories.AnimalRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {
    private final AnimalRepository repository;

    public AnimalService(AnimalRepository repository) {
        this.repository = repository;
    }


    public List<Animal> findAll() {
        return repository.findAll();
    }

    public void save(Animal animal) {
        repository.save(animal);
    }

    public Optional<Animal> findById(int id) {
        return repository.findById(id);
    }
}
