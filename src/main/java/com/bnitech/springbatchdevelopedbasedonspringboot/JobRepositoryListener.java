package com.bnitech.springbatchdevelopedbasedonspringboot;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobRepositoryListener implements JobExecutionListener {

  @Autowired
  private JobRepository jobRepository;

  @Override
  public void beforeJob(JobExecution jobExecution) {

  }

  @Override
  public void afterJob(JobExecution jobExecution) {
    final String jobName = jobExecution.getJobInstance().getJobName();

    final JobParameters requestDate = new JobParametersBuilder().addString("requestDate",
        "20210102").toJobParameters();

    final JobExecution lastJobExecution = jobRepository.getLastJobExecution(jobName, requestDate);
    if (lastJobExecution != null) {
      for (StepExecution stepExecution : lastJobExecution.getStepExecutions()) {
        final BatchStatus status = stepExecution.getStatus();
        System.out.println("status = " + status);
        final ExitStatus exitStatus = stepExecution.getExitStatus();
        System.out.println("exitStatus = " + exitStatus);
        final String stepName = stepExecution.getStepName();
        System.out.println("stepName = " + stepName);
      }
    }
  }
}
