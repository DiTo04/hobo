package se.tennander.hobo;

import java.util.Set;

import javax.inject.Inject;

import se.tennander.hobo.web.EventListener;

public class GameServer {

  private final EventListener eventListener;
  private final Set<Listener> listeners;

  @Inject
  GameServer(EventListener eventListener, Set<Listener> listeners) {
    this.eventListener = eventListener;
    this.listeners = listeners;
  }

  private void startAndWait() {
    listeners.forEach(Listener::activate);
    eventListener.listenOnEventsAndWait();
  }

  public static void main(String[] args) {
    GameComponent game = DaggerGameComponent.create();
    GameServer server = game.getServer();
    server.startAndWait();
  }
}
