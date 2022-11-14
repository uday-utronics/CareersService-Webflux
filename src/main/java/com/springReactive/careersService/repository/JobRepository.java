package com.springReactive.careersService.repository;

import com.springReactive.careersService.model.Job;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface JobRepository extends ReactiveCassandraRepository<Job,String> {

    //Flux<Job> findByjobId(String jobId);

    Mono<Job> findByjobId(String jobId);


    Mono<Boolean> existsByjobId(String jobId);
}
