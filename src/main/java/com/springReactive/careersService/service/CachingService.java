package com.springReactive.careersService.service;

import com.hazelcast.map.IMap;
import com.springReactive.careersService.model.Job;
import com.springReactive.careersService.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.Logger;
import reactor.util.Loggers;


public class CachingService {

    private static final Logger LOGGER = Loggers.getLogger(CachingService.class);



    private final IMap<String, Job> cache;
    private final JobRepository repository;


    public CachingService(IMap<String, Job> cache, JobRepository repository) {
        this.repository = repository;
        this.cache = cache;
    }

    public Mono<Job> findByjobId(String id) {
        return Mono.fromCompletionStage(() -> cache.getAsync(id))
                .doOnNext(p -> LOGGER.info("Employee with id " + p.getJobId() + " found in cache"))
                .switchIfEmpty(repository
                        .findByjobId(id)
                        .doOnNext(p -> {
                            cache.putAsync(p.getJobId(), p);
                            LOGGER.info("Employee with id " + p.getJobId() + " set in cache");
                        })
                );
    }
}
