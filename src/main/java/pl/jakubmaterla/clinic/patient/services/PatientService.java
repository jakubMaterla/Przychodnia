package pl.jakubmaterla.clinic.patient.services;

import org.springframework.stereotype.Service;
import pl.jakubmaterla.clinic.patient.model.Patient;
import pl.jakubmaterla.clinic.patient.repositories.PatientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private final PatientRepository repository;

    public PatientService(PatientRepository repository) {
        this.repository = repository;
    }


    public List<Patient> findAll() {
        return repository.findAll();
    }

    public void save(Patient patient) {
        repository.save(patient);
    }

    public Optional<Patient> findById(int id) {
        return repository.findById(id);
    }
}
