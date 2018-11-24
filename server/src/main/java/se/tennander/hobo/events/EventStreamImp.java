package se.tennander.hobo.events;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

@Singleton
class EventStreamImp implements EventStream {

  private final PublishSubject<Event> pubsub;

  @Inject
  EventStreamImp(PublishSubject<Event> pubsub) {
    this.pubsub = pubsub;
  }

  @Override
  public void publish(Event event) {
    pubsub.onNext(event);
  }

  @Override
  public Observable<Event> getObservable() {
    return pubsub;
  }
}
