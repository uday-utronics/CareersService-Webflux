package com.springReactive.careersService.service;

import com.springReactive.careersService.model.Employee;
import com.springReactive.careersService.model.Job;
import com.springReactive.careersService.repository.JobDtoRepository;
import com.springReactive.careersService.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class JobService {
    @Autowired
    private JobDtoRepository jobDtoRepository;

    @Autowired
    private WebClientService webClientService;
//    private WebClient webClient;
//
//    public JobService(WebClient webClient) {
//        this.webClient = webClient;
//    }
//
//    private String url = "localhost:8081/empmain/ExpgreaterThan/{exp}";
    public Flux<Employee> findEmployeeByJobid(String jobid) {
//
//        Mono<Job> jobMono = jobRepository.findByjobId(jobid);
//        return jobMono.flatMap(id -> id.getJavaExperience());

        return jobDtoRepository.findByjobId(jobid).flatMap(job -> {
                    return webClientService.fetchFindEmpSkillSet(job.getJavaExperience());
        });
    }
}
