package pl.jakubmaterla.clinic.employee;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;

@Configuration
public class TestConfiguration {

    @Bean
    @Primary
    @Profile("test")
    DataSource e2eTestDateSource() {
        var result = new DriverManagerDataSource("jdbc:h2:mem:testdb", "sa", "");
        result.setDriverClassName("org.h2.Driver");
        return result;
    }
}
