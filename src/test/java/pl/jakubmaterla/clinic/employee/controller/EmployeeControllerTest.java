package pl.jakubmaterla.clinic.employee.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import pl.jakubmaterla.clinic.employee.model.Employee;
import pl.jakubmaterla.clinic.employee.model.Position;
import pl.jakubmaterla.clinic.employee.repositories.EmployeeRepository;

import static org.assertj.core.api.Assertions.assertThat;

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


        var p1 = new Position();
        p1.setName("kierownik");
        var toPost = new Employee("Jakub", "Materla", 25);
        toPost.setPosition(p1);


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

    }

    @Test
    void http_returnsGivenEmployee() {
        // given
        int id = repository.save(new Employee("Jan", "Kowalski", 19)).getId();

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
        Employee emp1= new Employee("Lukasz", "Materla", 19);
        emp1.setPosition(p1);
        Employee emp2= new Employee("Bartek", "Materla", 19);
        emp2.setPosition(p2);
        repository.save(emp1);
        repository.save(emp2);

        // when
        Employee[] result = restTemplate.getForObject("http://localhost:" + port + "/employees", Employee[].class);

        // then
        assertThat(result).hasSize(2);
    }
}