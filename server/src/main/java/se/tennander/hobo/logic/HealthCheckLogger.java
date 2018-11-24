package se.tennander.hobo.logic;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.tennander.hobo.Listener;
import se.tennander.hobo.events.Event;

public class HealthCheckLogger implements Listener {
  private static Logger log = LoggerFactory.getLogger(HealthCheckLogger.class);
  private final Observable<Event.GotHealthCheck> healthChecks;

  @Inject
  HealthCheckLogger(Observable<Event.GotHealthCheck> healthChecks) {
    this.healthChecks = healthChecks;
  }


  @Override
  public void activate() {
    healthChecks
        .subscribeOn(Schedulers.io())
        .subscribe(e -> log.info("Got health check!"));
  }
}
