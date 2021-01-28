package pl.jakubmaterla.clinic.patient.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakubmaterla.clinic.patient.model.Race;

@Repository
public interface RaceRepository extends JpaRepository<Race, Integer> {
}
