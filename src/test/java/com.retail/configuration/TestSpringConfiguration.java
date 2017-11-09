package com.retail.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@Configuration
@Profile("unit-test")
public class TestSpringConfiguration {
  @Bean
  public RestTemplate getRestTemplate () { return Mockito.mock(RestTemplate.class); }

}
