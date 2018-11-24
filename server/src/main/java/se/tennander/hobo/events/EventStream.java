package se.tennander.hobo.events;

import se.tennander.hobo.State;

public interface EventStream {

  State handleEvent(Event event, State oldState);
  
}
