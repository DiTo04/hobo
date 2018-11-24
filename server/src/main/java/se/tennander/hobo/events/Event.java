package se.tennander.hobo.events;

public interface Event {
  static Event newConnection() {
    return new NewConnection();
  }

  static Event closedConnection() {
    return new ClosedConnection();
  }

  static Event Play(String player, int x, int y) {
    return new Play(player, x, y);
  }

  static Event gotHealthCheck() {
    return new GotHealthCheck();
  }

  class NewConnection implements Event {
  }

  class ClosedConnection implements Event {
  }

  class Play implements Event {
    public final String player;
    public final int x;
    public final int y;

    Play(String player, int x, int y) {
      this.player = player;
      this.x = x;
      this.y = y;
    }
  }

  class GotHealthCheck implements Event {
  }
}
