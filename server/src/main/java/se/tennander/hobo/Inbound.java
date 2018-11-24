package se.tennander.hobo;

import se.tennander.hobo.events.Event;

public interface Inbound {
  <T extends Event> void handle(Class<T> gotHealthCheckClass, EventHandler<T> handleGotHealthCheck);

  interface EventHandler<T extends Event> {
    State handleEvent(T event, State oldState);
  }
}
