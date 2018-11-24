package se.tennander.hobo.game;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.tennander.hobo.Inbound;
import se.tennander.hobo.Listener;
import se.tennander.hobo.State;
import se.tennander.hobo.events.Event;

public class HealthCheckLogger implements Listener {
  private static Logger log = LoggerFactory.getLogger(HealthCheckLogger.class);

  @Inject
  HealthCheckLogger() {
  }

  @Override
  public void wire(Inbound eventStream) {
    eventStream.handle(Event.GotHealthCheck.class, this::handleGotHealthCheck);
  }

  private State handleGotHealthCheck(Event.GotHealthCheck event, State oldState) {
    log.info("Got healthCheck");
    return oldState;
  }
}
