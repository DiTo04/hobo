package se.tennander.hobo.web;

import javax.inject.Inject;

import io.javalin.Javalin;
import io.javalin.json.JavalinJson;
import io.javalin.websocket.WsSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.tennander.hobo.State;
import se.tennander.hobo.events.EventStream;
import se.tennander.hobo.events.Event;

public class EventListener {
  private static Logger log = LoggerFactory.getLogger(EventListener.class);
  private final Javalin javalin;
  private final EventStream eventStream;
  private State currentState = State.newGame();

  @Inject
  EventListener(Javalin javalin, EventStream eventStream) {
    this.javalin = javalin;
    this.eventStream = eventStream;
  }

  public void listenOnEventsAndWait() {
    javalin.ws("/websocket", ws -> {
      ws.onConnect(this::onConnect);
      ws.onClose(this::onClose);
      ws.onError(this::onError);
      ws.onMessage(this::onMessage);
    });
    javalin.get("/health", ctx -> {
      ctx.json("Okey");
      log.info("Got health check!");
      eventStream.handleEvent(Event.gotHealthCheck(), currentState);
    });
    javalin.get("/current_state", ctx -> {
      ctx.json(currentState);
    });

    javalin.start(8080);
  }

  /**
   * Game connected.
   * @param session new session
   */
  private void onConnect(WsSession session) {
    log.info("Got session");
    currentState = eventStream.handleEvent(Event.newConnection(), currentState);
    session.send(JavalinJson.toJson(currentState));
  }

  private void onClose(WsSession session,  int statusCode, String reason) {
    log.info("Closed session");
    currentState = eventStream.handleEvent(Event.closedConnection(), currentState);
  }

  private void onError(WsSession session, Throwable throwable) {
    log.warn("Got error: \"{}\"",throwable.getClass().getSimpleName());
  }

  private void onMessage(WsSession session, String message) {
    log.info("Got message: \"{}\"", message);
    PlaySocketEvent event = PlaySocketEvent.fromString(message);
    currentState = eventStream.handleEvent(Event.Play(event.player, event.x, event.y), currentState);
    session.send(JavalinJson.toJson(currentState));
  }
}
