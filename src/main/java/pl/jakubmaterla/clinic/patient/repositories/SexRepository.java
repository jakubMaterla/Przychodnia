package pl.jakubmaterla.clinic.patient.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakubmaterla.clinic.patient.model.Sex;

@Repository
public interface SexRepository extends JpaRepository<Sex, Integer> {
}
