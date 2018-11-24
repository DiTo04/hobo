package se.tennander.hobo.game;

import javax.inject.Inject;

import se.tennander.hobo.Inbound;
import se.tennander.hobo.Listener;
import se.tennander.hobo.State;
import se.tennander.hobo.events.Event;

public class GameSession implements Listener {

  @Inject
  public GameSession() {
  }

  @Override
  public void wire(Inbound eventStream) {
    eventStream.handle(Event.NewConnection.class, this::newConnection);
    eventStream.handle(Event.Play.class, this::handlePlay);
  }

  private State handlePlay(Event.Play play, State state) {
    state.tiles.get(play.x).get(play.y).marker = State.PlayerMark.valueOf(play.player);
    return state;
  }

  private State newConnection(Event.NewConnection connectionEvent, State state) {
    return State.newGame();
  }
}
