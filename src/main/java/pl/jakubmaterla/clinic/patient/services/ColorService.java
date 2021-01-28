package pl.jakubmaterla.clinic.patient.services;

import org.springframework.stereotype.Service;
import pl.jakubmaterla.clinic.patient.model.Color;
import pl.jakubmaterla.clinic.patient.repositories.ColorRepository;

import java.util.List;

@Service
public class ColorService {
    private final ColorRepository repository;

    public ColorService(ColorRepository repository) {
        this.repository = repository;
    }

    public List<Color> readAll() {
        return repository.findAll();
    }
}
