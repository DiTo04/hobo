package se.tennander.hobo;

import java.util.Set;

import javax.inject.Inject;

import se.tennander.hobo.web.EventListener;
import se.tennander.hobo.web.StaticFileServer;

public class GameServer {

  private final EventListener eventListener;
  private final Set<Listener> listeners;
  private final StaticFileServer fileServer;

  @Inject
  GameServer(
      EventListener eventListener,
      Set<Listener> listeners,
      StaticFileServer fileServer) {
    this.eventListener = eventListener;
    this.listeners = listeners;
    this.fileServer = fileServer;
  }

  private void startAndWait() {
    listeners.forEach(Listener::activate);
    fileServer.start();
    eventListener.listenOnEventsAndWait();
  }

  public static void main(String[] args) {
    GameComponent game = DaggerGameComponent.create();
    GameServer server = game.getServer();
    server.startAndWait();
  }
}
