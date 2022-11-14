package com.springReactive.careersService.controller;

import com.springReactive.careersService.model.Employee;
import com.springReactive.careersService.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class JobController {

    @Autowired
    private JobService jobService;


    @GetMapping("/findEmpForJobID/{jobId}")
    Flux<Employee> findByJobId(@PathVariable String jobId){
       return jobService.findEmployeeByJobid(jobId);
    }
}
