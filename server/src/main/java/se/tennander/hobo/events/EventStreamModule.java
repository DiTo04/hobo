package se.tennander.hobo.events;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

@Module
public abstract class EventStreamModule {

  @Binds
  abstract EventStream getEventStream(EventStreamImp impl);

  @Provides
  @Singleton
  static PublishSubject<Event> getPublishSubject() {
    return PublishSubject.create();
  }

  @Provides
  static Observable<Event.GotHealthCheck> getGotHealthCheckEvents(EventStream eventStream) {
    return eventStream.getObservable()
        .filter(e -> e instanceof Event.GotHealthCheck)
        .map(Event.GotHealthCheck.class::cast);
  }

  @Provides
  static Observable<Event.ClosedConnection> getClosedConnectionEvents(EventStream eventStream) {
    return eventStream.getObservable()
        .filter(e -> e instanceof Event.ClosedConnection)
        .map(Event.ClosedConnection.class::cast);
  }

  @Provides
  static Observable<Event.NewConnection> getNewConnectionEvents(EventStream eventStream) {
    return eventStream.getObservable()
        .filter(e -> e instanceof Event.NewConnection)
        .map(Event.NewConnection.class::cast);
  }

  @Provides
  static Observable<Event.Play> getPlayEvents(EventStream eventStream) {
    return eventStream.getObservable()
        .filter(e -> e instanceof Event.Play)
        .map(Event.Play.class::cast);
  }
}
