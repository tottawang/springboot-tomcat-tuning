package com.sample.event;

import java.util.Optional;

public interface EventHandler {
  Optional<String> getMessage(String event);
}
