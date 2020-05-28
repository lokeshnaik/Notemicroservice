package com.bridgelabz.notemicroservice.configuration;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@SpringBootApplication
@Component
@Configuration
public class DemoApplication 
{      
   @Bean
   public RestTemplate getRestTemplate() {
      return new RestTemplate();
   }  
}
