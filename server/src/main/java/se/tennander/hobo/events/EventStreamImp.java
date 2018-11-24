package se.tennander.hobo.events;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import se.tennander.hobo.Inbound;
import se.tennander.hobo.Listener;
import se.tennander.hobo.State;

@Singleton
class EventStreamImp implements EventStream {

  private final Set<Listener> listeners;
  private final Map<Class<? extends Event>, Inbound.EventHandler<Event>> handlers = new HashMap<>();

  @Inject
  EventStreamImp(Set<Listener> listeners) {
    this.listeners = listeners;
  }

  void activate() {
    listeners.forEach(l -> l.wire(this::handle));
  }

  @Override
  public State handleEvent(Event event, State oldState) {
    return handlers.get(event.getClass()).handleEvent(event, oldState);
  }

  private <T extends Event> void handle(Class<T> gotHealthCheckClass, Inbound.EventHandler<T> handleGotHealthCheck) {
    handlers.put(gotHealthCheckClass, (e, s) -> handleGotHealthCheck.handleEvent(gotHealthCheckClass.cast(e), s));
  }

}
