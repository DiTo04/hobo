package se.tennander.hobo.events;

import dagger.Module;
import dagger.Provides;

@Module
public class EventStreamModule {

  @Provides
  EventStream getEventStream(EventStreamImp impl) {
    impl.activate();
    return impl;
  }

}
