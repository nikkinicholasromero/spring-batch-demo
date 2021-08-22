package com.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            jdbcTemplate.query("SELECT FIRST_NAME, LAST_NAME FROM PEOPLE",
                (rs, row) -> Person.builder()
                    .firstName(rs.getString(1))
                    .lastName(rs.getString(2)))
                .forEach(person -> log.info("Found " + person + " in the database. "));
        }
    }
}
