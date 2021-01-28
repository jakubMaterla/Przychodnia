package pl.jakubmaterla.clinic.patient.services;

import org.springframework.stereotype.Service;
import pl.jakubmaterla.clinic.patient.model.Race;
import pl.jakubmaterla.clinic.patient.repositories.RaceRepository;

import java.util.List;

@Service
public class RaceService {
    private final RaceRepository repository;

    public RaceService(RaceRepository repository) {
        this.repository = repository;
    }

    public List<Race> readAll() {
        return repository.findAll();
    }
}
