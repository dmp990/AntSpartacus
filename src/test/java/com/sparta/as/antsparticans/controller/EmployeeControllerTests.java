package com.sparta.as.antsparticans.controller;

import com.sparta.as.antsparticans.model.dtos.EmployeeDTO;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebFluxTest
public class EmployeeControllerTests {


    @BeforeEach
    public void setup() {
        /*EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(100);
        employeeDTO.setGender("M");
        employeeDTO.setFirstName("Test");
        employeeDTO.setLastName("Employee");
        employeeDTO.setBirthDate(LocalDate.parse("2000-10-10"));
        employeeDTO.setHireDate(LocalDate.parse("2020-10-10"));*/

        Mono<EmployeeDTO> response = WebClient.create("http://localhost:8080/employees/500001").get().retrieve()
                .bodyToMono(EmployeeDTO.class);

        EmployeeDTO employeeDTO = response.block();
    }

    @Test
    @DisplayName("Check if employees/id endpoint returns correct employee by id")
    void checkIfEmployeesEndpointReturnsCorrectEmployeeById() {
        Mono<EmployeeDTO> response = WebClient.create("http://localhost:8080/employees/10001")
                .get()
                .retrieve()
                .bodyToMono(EmployeeDTO.class);

        Assertions.assertEquals(10001, response.block().getId());
    }


    @Test
    @DisplayName("Check if employees endpoint returns all employees")
    void checkIfEmployeesEndpointReturnsAllEmployees() {
        Mono<List<EmployeeDTO>> response = WebClient.create("http://localhost:8080/employees")
                .get()
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<EmployeeDTO>>() {
                });

        Assertions.assertEquals(1000
                , response.block().size());
    }

    @Test
    @DisplayName("Check if employees/id endpoint returns a 404 status code if the employee does not exist")
    void checkIfEmployeesEndpointReturnsA404CodeIfEmployeeDoesNotExist() throws IOException {
        HttpUriRequest request = new HttpGet("http://localhost:8080/employees/1");
        HttpResponse httpResponse = HttpClientBuilder.create().build().
                execute(request);
        Assertions.assertEquals(
                httpResponse.getStatusLine().getStatusCode(),
                404);
    }

    WebClient webClient = WebClient.create();

    @Test
    @DisplayName("Check if employee last name is successfully changed")
    void checkIfEmployeeLastNameIsChanged() {
        EmployeeDTO e = new EmployeeDTO();
        e.setLastName("Changed");
        Mono<EmployeeDTO> response = webClient.put().uri("http://localhost:8080/employees/500001")
                .body(Mono.just(e), EmployeeDTO.class)
                .retrieve()
                .bodyToMono(EmployeeDTO.class);

        Assertions.assertEquals("Changed", response.block().getLastName());
    }

    @Test
    @DisplayName("Check if new employee has been created")
    void checkIfNewEmployeeHasBeenCreated() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(500002);
        employeeDTO.setGender("M");
        employeeDTO.setFirstName("WebClient");
        employeeDTO.setLastName("TestEmployee");
        employeeDTO.setBirthDate(LocalDate.parse("2000-10-10"));
        employeeDTO.setHireDate(LocalDate.parse("2020-10-10"));
        Mono<EmployeeDTO> response = webClient.post().uri("http://localhost:8080/employees")
                .body(Mono.just(employeeDTO), EmployeeDTO.class)
                .retrieve()
                .bodyToMono(EmployeeDTO.class);

        Assertions.assertEquals(500002, response.block().getId());
    }

    @Autowired
    private WebTestClient webTestClient;

    /*@Test
    @DisplayName("Check if an employee has been deleted")
    void checkIfEmployeeHasBeenDeleted() {
        deleteEmployee();
        Mono<String> errorResponse = webClient.get().uri("http://localhost:8080/employees/500002")
                .retrieve().bodyToMono(String.class);
        Assertions.assertEquals(errorResponse.block(), "Employee not found");
    }

    public void deleteEmployee() {
        webClient
                .method(HttpMethod.DELETE)
                .uri("http://localhost:8080/employees/500002")
                .retrieve()
                .bodyToMono(Void.class)
                .subscribe();
    }*/
    /*
    @Test
    @DisplayName("Check if employees/id endpoint returns an error message if the employee does not exist")
    void checkIfEmployeesEndpointReturnsAnErrorMessageIfEmployeeDoesNotExist() throws IOException {
        HttpUriRequest request = new HttpGet("http://localhost:8080/employees/1");
        HttpResponse httpResponse = HttpClientBuilder.create().build().
                execute(request);
        System.out.println(httpResponse);
    }
     */

    @AfterEach
    public void teardown() {
        EmployeeDTO e = new EmployeeDTO();
        e.setLastName("Employee");
        e.setFirstName("NotATest");

        Mono<EmployeeDTO> response = webClient.put().uri("http://localhost:8080/employees/500001")
                .body(Mono.just(e), EmployeeDTO.class)
                .retrieve()
                .bodyToMono(EmployeeDTO.class);
    }

}
