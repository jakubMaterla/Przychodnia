package pl.jakubmaterla.clinic.patient.services;

import org.springframework.stereotype.Service;
import pl.jakubmaterla.clinic.patient.model.Size;
import pl.jakubmaterla.clinic.patient.repositories.SizeRepository;

import java.util.List;

@Service
public class SizeService {
    private final SizeRepository repository;

    public SizeService(SizeRepository repository) {
        this.repository = repository;
    }

    public List<Size> readAll() {
        return repository.findAll();
    }
}
