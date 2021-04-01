package pl.jakubmaterla.clinic.patient.services;

import org.springframework.stereotype.Service;
import pl.jakubmaterla.clinic.patient.model.Medicine;
import pl.jakubmaterla.clinic.patient.repositories.MedicineRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MedicineService {
    private final MedicineRepository repository;

    public MedicineService(MedicineRepository repository) {
        this.repository = repository;
    }

    public List<Medicine> findAll() {
        return repository.findAll();
    }

    public void save(Medicine toSave) {
        repository.save(toSave);
    }

    public Optional<Medicine> findById(int Id) {
        return repository.findById(Id);
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
