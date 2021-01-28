package pl.jakubmaterla.clinic.patient.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "owners")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private int age;
    @OneToMany
    @JoinColumn(name = "animal_id")
    private List<Animal> animals;
}
