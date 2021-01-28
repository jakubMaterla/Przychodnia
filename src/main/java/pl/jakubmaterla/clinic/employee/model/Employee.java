package pl.jakubmaterla.clinic.employee.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private int age;
    private int salary;
    private int bonus;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id")
    private Group group;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "position_id")
    private Position position;

    public Employee() {
    }

    public Employee(String name, String surname, int age, int salary, int bonus) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.salary = salary;
        this.bonus = bonus;
    }
}
