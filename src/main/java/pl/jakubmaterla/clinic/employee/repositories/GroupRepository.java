package pl.jakubmaterla.clinic.employee.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakubmaterla.clinic.employee.model.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
}
