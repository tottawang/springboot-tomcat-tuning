package com.sample.conf;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

@Configuration
public class ApplicationConfig {

  @Bean
  public Client getClient() {
    ClientConfig config = new ClientConfig(new JacksonJaxbJsonProvider());
    return ClientBuilder.newClient(config);
  }

}
