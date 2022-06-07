package com.bnitech.springbatchdevelopedbasedonspringboot;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class StepExecutionConfiguration {

  private final JobBuilderFactory jobBuilderFactory;
  private final StepBuilderFactory stepBuilderFactory;

  @Bean
  public Job BatchJob() {
    return jobBuilderFactory.get("job")
        .start(step1())
        .next(step2())
        .build();
  }

  @Bean
  public Step step1() {
    return stepBuilderFactory.get("step1")
        .tasklet((stepContribution, chunkContext) -> {
          System.out.println("step1 was executed");
          return RepeatStatus.FINISHED;
        })
        .build();
  }

  @Bean
  public Step step2() {
    return stepBuilderFactory.get("step2")
        .tasklet((stepContribution, chunkContext) -> {
          System.out.println("step2 was executed");
          throw new RuntimeException("step2 has failed");
        })
        .build();
  }

  @Bean
  public Step step3() {
    return stepBuilderFactory.get("step3")
        .tasklet((stepContribution, chunkContext) -> {
          System.out.println("step3 was executed");
          return RepeatStatus.FINISHED;
        })
        .build();
  }

}