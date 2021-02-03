package pl.jakubmaterla.clinic.patient.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    private double weight;
    private String birthDate;
    //private int medicine_id;
    /*@OneToOne(cascade = CascadeType.ALL)
    private Owner owner;*/
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "race_id")
    private Race race;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "color_eye")
    private Color color;
    @OneToOne(cascade = CascadeType.ALL)
    private Sex sex;
    @OneToOne(cascade = CascadeType.ALL)
    private Size size;
    /*@OneToMany(mappedBy = "patient")
    private List<Treatment> treatments;*/


    public Patient() {
    }

    public Patient(String name) {
        this.name = name;
    }

}
