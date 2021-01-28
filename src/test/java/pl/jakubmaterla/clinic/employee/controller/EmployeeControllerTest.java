package pl.jakubmaterla.clinic.employee.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import pl.jakubmaterla.clinic.employee.model.Employee;
import pl.jakubmaterla.clinic.employee.model.Group;
import pl.jakubmaterla.clinic.employee.model.Position;
import pl.jakubmaterla.clinic.employee.repositories.EmployeeRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private EmployeeRepository repository;

    @Test
    void httpGet_createNewEmployee() {
        // given
        int sizeBeforePost = repository.findAll().size();
        int beforePostEmployeeIdCheck = sizeBeforePost + 1;

        var g1= new Group();
        g1.setName("kierownictwo");
        var p1 = new Position();
        p1.setName("kierownik");
        var toPost = new Employee("Jakub", "Materla", 25, 1500, 200);
        toPost.setPosition(p1);
        toPost.setGroup(g1);

        //when
        int postedEmployeeId = restTemplate.postForObject(
                "http://localhost:" + port + "/employees", toPost, Employee.class).getId();
        Employee postedEmployee = restTemplate.getForObject(
                "http://localhost:" + port + "/employees/" + beforePostEmployeeIdCheck, Employee.class);

        // then
        assertThat(postedEmployeeId).isEqualTo(beforePostEmployeeIdCheck);
        assertThat(postedEmployee).hasFieldOrPropertyWithValue("name", "Jakub");
        assertThat(postedEmployee).hasFieldOrPropertyWithValue("surname", "Materla");
        assertThat(postedEmployee).hasFieldOrPropertyWithValue("age", 19);
        assertThat(postedEmployee).hasFieldOrPropertyWithValue("salary", 15000);
        assertThat(postedEmployee).hasFieldOrPropertyWithValue("bonus", 2000);
    }

    @Test
    void http_returnsGivenEmployee() {
        // given
        int id = repository.save(new Employee("Jan", "Kowalski", 19, 15000, 2000)).getId();

        // when
        Employee result = restTemplate.getForObject("http://localhost:" + port + "/employees/" + id, Employee.class);

        // then
        assertThat(result);
    }

    @Test
    void httpGet_returnsAllEmployee() {
        // given
       // int initial = repository.findAll().size();
        var p1 = new Position();
        var p2 = new Position();
        p1.setName("stanowisko1");
        p2.setName("stanowisko2");
        Group g1 = new Group();
        Group g2 = new Group();
        g2.setName("grupa1");
        g1.setName("grupa2");
        Employee emp1= new Employee("Lukasz", "Materla", 19, 150000, 2000);
        emp1.setGroup(g1);
        emp1.setPosition(p1);
        Employee emp2= new Employee("Bartek", "Materla", 19, 14000, 2400);
        emp2.setGroup(g2);
        emp2.setPosition(p2);
        repository.save(emp1);
        repository.save(emp2);

        // when
        Employee[] result = restTemplate.getForObject("http://localhost:" + port + "/employees", Employee[].class);

        // then
        assertThat(result).hasSize(2);
    }
}