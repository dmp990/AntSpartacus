package com.sparta.as.antsparticans.controller;

import com.sparta.as.antsparticans.model.dtos.EmployeeDTO;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

public class EmployeeControllerTests {

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
}
