package com.springReactive.careersService.router;

import com.springReactive.careersService.handler.JobHandler;
import com.springReactive.careersService.model.Job;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class JobRouter {

    @Bean
    @RouterOperations({
            @RouterOperation(path = "/createJobProfile", produces = {
                    MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST, beanClass = JobHandler.class, beanMethod = "save",
                    operation = @Operation(operationId = "save", responses = {
                            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Job.class))),
                            @ApiResponse(responseCode = "400", description = "Invalid Employee details supplied")}
                            , requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = Job.class)))
                    ))
    })
    public RouterFunction<ServerResponse> jobRoutes(JobHandler jobHandler){
        return route()
                .POST("/createJobProfile", request -> jobHandler.save(request) )
                .GET("/getJobProfileFromCache/{jobId}", request -> jobHandler.findById(request))
                .build();
    }

}
