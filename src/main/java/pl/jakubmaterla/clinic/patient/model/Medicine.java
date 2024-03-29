package pl.jakubmaterla.clinic.patient.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "medicines")
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String unicode;
    private String name;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "medicineid")
    private List<Patient> patient;
}
