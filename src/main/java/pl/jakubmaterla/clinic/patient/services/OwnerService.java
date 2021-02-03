package pl.jakubmaterla.clinic.patient.services;

import org.springframework.stereotype.Service;
import pl.jakubmaterla.clinic.patient.model.Owner;
import pl.jakubmaterla.clinic.patient.repositories.OwnerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {
    private final OwnerRepository repository;

    public OwnerService(OwnerRepository repository) {
        this.repository = repository;
    }

    public List<Owner> readAll() {
        return repository.findAll();
    }

    public void save(Owner owner) {
        repository.save(owner);
    }

    public Optional<Owner> findById(int id) {
        return repository.findById(id);
    }
}
