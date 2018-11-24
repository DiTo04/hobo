package se.tennander.hobo;

import javax.inject.Inject;

import se.tennander.hobo.web.EventListener;
import se.tennander.hobo.web.StaticFileServer;

public class GameServer {

  private final EventListener eventListener;
  private final StaticFileServer fileServer;

  @Inject
  GameServer(
      EventListener eventListener,
      StaticFileServer fileServer) {
    this.eventListener = eventListener;
    this.fileServer = fileServer;
  }

  private void startAndWait() {
    fileServer.start();
    eventListener.listenOnEventsAndWait();
  }

  public static void main(String[] args) {
    GameComponent game = DaggerGameComponent.create();
    GameServer server = game.getServer();
    server.startAndWait();
  }
}
