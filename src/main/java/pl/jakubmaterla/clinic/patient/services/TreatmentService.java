package pl.jakubmaterla.clinic.patient.services;

import org.springframework.stereotype.Service;
import pl.jakubmaterla.clinic.patient.model.Treatment;
import pl.jakubmaterla.clinic.patient.repositories.TreatmentRepository;

import java.util.List;

@Service
public class TreatmentService {
    private final TreatmentRepository repository;

    public TreatmentService(TreatmentRepository repository) {
        this.repository = repository;
    }


    public void save(Treatment toSave) {
        repository.save(toSave);
    }

    public List<Treatment> findAll() {
        return repository.findAll();
    }
}
