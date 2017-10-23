package com.sample.event;

import java.util.Optional;

public class MyEventHandler implements EventHandler {

  @Override
  public Optional<String> getMessage(String event) {
    return Optional.of("message");
  }

}
