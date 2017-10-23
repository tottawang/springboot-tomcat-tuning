package com.sample.resources;

import java.util.Optional;
import java.util.function.Function;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sample.event.EventHandler;

@Path("/api")
@Produces("text/plain")
@Component
public class SampleResource {

  @Autowired
  private Client client;

  @Autowired
  Function<String, EventHandler> handlersByEventType;

  @POST
  @Path("/notify")
  public String verifyEventType() {
    EventHandler eventHandler = handlersByEventType.apply("fs.file.added");
    // System.out.println("[" + Thread.currentThread().getName() + "]" + " no event ");
    if (eventHandler == null) {
      return null;
    }
    Optional<String> message = eventHandler.getMessage("eventPayload");
    // System.out.println("[" + Thread.currentThread().getName() + "]" + message.get());
    return message.get();
  }

  @POST
  @Path("/perf")
  public String getUsers() {
    String text = client.target("https://requestb.in/1ed5dcn1").request(MediaType.APPLICATION_JSON)
        .get(String.class);
    System.out.println("[" + Thread.currentThread().getName() + "]" + " finished: " + text);
    return text;
  }

}
