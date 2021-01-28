package pl.jakubmaterla.clinic.patient.services;

import org.springframework.stereotype.Service;
import pl.jakubmaterla.clinic.patient.repositories.TreatmentRepository;

@Service
public class TreatmentService {
    private final TreatmentRepository repository;

    public TreatmentService(TreatmentRepository repository) {
        this.repository = repository;
    }


}
