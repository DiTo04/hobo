package se.tennander.hobo.events;

import io.reactivex.Observable;

public interface EventStream {
  void publish(Event event);
  Observable<Event> getObservable();
}
