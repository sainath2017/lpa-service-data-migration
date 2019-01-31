package com.wfs.landpricing;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wfs.landpricing.util.JsonUtil;
import java.util.concurrent.Executor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableAsync
@ComponentScan(basePackages = {"com.wfs.*"})
public class Startup extends AsyncConfigurerSupport {

  public static void main(String[] args) {
    SpringApplication.run(Startup.class, args);
  }


  @Override
  public Executor getAsyncExecutor() {
    SimpleAsyncTaskExecutor simpleAsyncTaskExecutor = new SimpleAsyncTaskExecutor();
    simpleAsyncTaskExecutor.setThreadNamePrefix("pnr-notifications-delivery");
    return simpleAsyncTaskExecutor;
  }

  @Bean
  public JsonUtil json(ObjectMapper mapper) {
    return new JsonUtil(mapper);
  }


  @Bean
  public Executor taskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(350);
    executor.setMaxPoolSize(350);
    executor.setQueueCapacity(5000000);
    executor.setThreadNamePrefix("HistoricalSave-");
    executor.initialize();
    return executor;
  }

}
