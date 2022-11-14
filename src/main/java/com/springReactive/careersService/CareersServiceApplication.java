package com.springReactive.careersService;

import com.hazelcast.core.HazelcastInstance;
import com.springReactive.careersService.connection.DataStaxAstraProperties;
import com.springReactive.careersService.repository.JobRepository;
import com.springReactive.careersService.service.CachingService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.nio.file.Path;

@SpringBootApplication
@EnableConfigurationProperties(DataStaxAstraProperties.class)
@OpenAPIDefinition(info = @Info(
		title = "Updates Service application",
		version = "1.0",
		description = "Updates Service"
))
public class CareersServiceApplication {

	@Bean
	public CachingService service(HazelcastInstance instance, JobRepository repository) {
		return new CachingService(instance.getMap("Job"), repository);
	}


	public static void main(String[] args) {
		SpringApplication.run(CareersServiceApplication.class, args);
	}

	@Bean
	public CqlSessionBuilderCustomizer sessionBuilderCustomizer(DataStaxAstraProperties astraProperties) {
		Path bundle = astraProperties.getSecureConnectBundle().toPath();
		return builder -> builder.withCloudSecureConnectBundle(bundle);
	}
}
