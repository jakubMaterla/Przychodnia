package pl.jakubmaterla.clinic.patient.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "patients")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    private double weight;
    private String birthDate;
    @ManyToOne(cascade = CascadeType.ALL)
    private Owner owner;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "race_id")
    private Race race;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "color_eye")
    private Color color;
    @OneToOne(cascade = CascadeType.ALL)
    private Sex sex;
    @OneToOne(cascade = CascadeType.ALL)
    private Size size;
    @OneToMany(mappedBy = "animal")
    private List<Treatment> treatments;
    @OneToMany(mappedBy = "animal")
    private List<Medicine> medicines;

    public Animal() {
    }

    public Animal(String name) {
        this.name = name;
    }

}
