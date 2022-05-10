package com.bnitech.springbatchdevelopedbasedonspringboot;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class SpringBatchDevelopedBasedOnSpringBootApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBatchDevelopedBasedOnSpringBootApplication.class, args);
  }

}
