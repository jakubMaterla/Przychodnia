package pl.jakubmaterla.clinic.patient.services;

import org.springframework.stereotype.Service;
import pl.jakubmaterla.clinic.patient.model.Sex;
import pl.jakubmaterla.clinic.patient.repositories.SexRepository;

import java.util.List;

@Service
public class SexService {
    private final SexRepository repository;

    public SexService(SexRepository repository) {
        this.repository = repository;
    }

    public List<Sex> readAll() {
        return repository.findAll();
    }
}
