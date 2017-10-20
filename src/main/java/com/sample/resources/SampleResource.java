package com.sample.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

@Path("/api")
@Produces("text/plain")
public class SampleResource {

  @Autowired
  private Client client;

  @GET
  @Path("/perf")
  public String getUsers() {
    String text = client.target("https://requestb.in/1ed5dcn1").request(MediaType.APPLICATION_JSON)
        .get(String.class);
    System.out.println("[" + Thread.currentThread().getName() + "]" + " finished: " + text);
    return text;
  }

}
