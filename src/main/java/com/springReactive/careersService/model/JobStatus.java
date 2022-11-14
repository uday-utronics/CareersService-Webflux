package com.springReactive.careersService.model;

import lombok.*;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JobStatus {
    private String jobId;

    private String jobName;
    private double javaExperience;
    private double springExperience;
    private String status;

}
