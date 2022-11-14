package com.springReactive.careersService.repository;

import com.springReactive.careersService.model.Job;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface JobDtoRepository extends ReactiveCassandraRepository<Job,String> {

    Flux<Job> findByjobId(String jobId);
}
