package com.springReactive.careersService.handler;

import com.springReactive.careersService.model.Job;
import com.springReactive.careersService.model.JobStatus;
import com.springReactive.careersService.repository.JobRepository;
import com.springReactive.careersService.service.CachingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class JobHandler {


    @Autowired
    private JobRepository jobRepository;

    Logger logging = LoggerFactory.getLogger(JobHandler.class);
    @Autowired
    private CachingService cachingService;
    public Mono<ServerResponse> save(ServerRequest request) {
//       Mono<Job> jobMono = request.bodyToMono(Job.class).log();
////        System.out.println(jobMono.toString());
//        request.
//        Mono<String> jobStatusMono;
        return request.bodyToMono(Job.class)
                .flatMap(jobRepository::save)
                .flatMap(ServerResponse.status(HttpStatus.CREATED)::bodyValue);

       // return jobRepository.existsByjobId()
//        return request.bodyToMono(Job.class)
//                .flatMap(job ->jobRepository.existsByjobId(job.getJobId()).flatMap(
//                                exists ->{
//                                    if(exists){
//
//                                        return Mono.zip(request.bodyToMono(Job.class),Mono.just("Already Exists")).log().map(
//                                                t -> new JobStatus(t.getT1().getJobId(),
//                                                        t.getT1().getJobName(),
//                                                        t.getT1().getJavaExperience(),
//                                                        t.getT1().getSpringExperience(),t.getT2().toString())
//                                        ).flatMap(ServerResponse.status(HttpStatus.CREATED)::bodyValue);
//                                    }
//                                    else {
//                                        return Mono.zip(request.bodyToMono(Job.class).flatMap(res -> {
//                                                    logging.debug(res.toString());
//                                                    System.out.println(res.toString());
//                                                    return jobRepository.save(res);}),Mono.just("Created")).log()
//                                                .flatMap(ServerResponse.status(HttpStatus.CREATED)::bodyValue);
//                                    }
//                                })
//                        );
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        String JobId = request.pathVariable("jobId");
        var jobs = cachingService.findByjobId(JobId);
        return ServerResponse.ok().body(jobs,Job.class);
    }


}
