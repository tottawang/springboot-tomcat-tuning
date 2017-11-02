package com.sample.resources;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sample.event.EventHandler;

@Path("/api")
@Produces("text/plain")
@Component
public class SampleResource {

  private static final Logger log = LoggerFactory.getLogger(SampleResource.class);

  @Autowired
  private Client client;

  @Autowired
  Function<String, EventHandler> handlersByEventType;

  @POST
  @Path("/notify")
  public String verifyEventType() {
    String id = UUID.randomUUID().toString();
    long start = System.currentTimeMillis();
    log.info("start log " + id);
    EventHandler eventHandler = handlersByEventType.apply("fs.file.added");
    // System.out.println("[" + Thread.currentThread().getName() + "]" + " no event ");
    if (eventHandler == null) {
      return null;
    }
    log.info("process1 log" + id);
    log.info("process2 log" + id);
    log.info("process3 log" + id);
    Optional<String> message = eventHandler.getMessage("eventPayload");
    // System.out.println("[" + Thread.currentThread().getName() + "]" + message.get());
    long end = System.currentTimeMillis();
    log.info("Time taken to finish logs " + (end - start) + " milliseconds");
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

  @POST
  @Path("/log")
  public void writeLog() {
    String id = UUID.randomUUID().toString();
    long start = System.currentTimeMillis();
    log.info("start log " + id);
    log.info("process1 log" + id);
    log.info("process2 log" + id);
    log.info("process3 log" + id);
    log.info("process4 log" + id);
    log.info("process5 log" + id);
    log.info("end log " + id);
    long end = System.currentTimeMillis();
    log.info("Time taken to finish logs " + (end - start) + " milliseconds");
  }

}
