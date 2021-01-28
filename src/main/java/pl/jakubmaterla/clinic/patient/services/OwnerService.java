package pl.jakubmaterla.clinic.patient.services;

import org.springframework.stereotype.Service;
import pl.jakubmaterla.clinic.patient.model.Owner;
import pl.jakubmaterla.clinic.patient.repositories.OwnerRepository;

import java.util.List;

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
}
