package com.springReactive.careersService.service;

import com.springReactive.careersService.model.Employee;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class WebClientService {

    private final WebClient webClient;


    public WebClientService(WebClient.Builder builder ) {
        webClient = builder.baseUrl("http://localhost:8081/").build();
    }
    private String url = "empmain/ExpgreaterThan/";
    Flux<Employee> fetchFindEmpSkillSet(double javaexp){
        return webClient
                .get()
                .uri(url+javaexp)
                .retrieve().bodyToFlux(Employee.class);

    }
}
