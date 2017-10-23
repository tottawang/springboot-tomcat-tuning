package com.sample.conf;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sample.event.EventHandler;
import com.sample.event.MyEventHandler;

@Configuration
public class EventHandlerConfiguration {

  @Value("${EVENT_NAMES}")
  private String eventNames;

  @Bean
  public EventHandler anotherEventHandler() {
    return (e) -> {
      return null;
    };
  }

  @Bean
  public MyEventHandler myEventHandler() {
    return new MyEventHandler();
  }

  @Bean
  public Function<String, EventHandler> registeredHandlers() {
    Map<String, EventHandler> handlers = new HashMap<>();
    Arrays.asList(eventNames.split(",")).stream().forEach(eventName -> {
      if (eventName.startsWith("fs")) {
        handlers.put(eventName, myEventHandler());
      }
    });
    return (s) -> handlers.get(s);
  }

}
