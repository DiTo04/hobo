package se.tennander.hobo.web;

public class PlaySocketEvent {

  final String player;
  final int x;
  final int y;

  private PlaySocketEvent(String player, int x, int y) {
    this.player = player;
    this.x = x;
    this.y = y;
  }

  public static PlaySocketEvent fromString(String msg) {
    String[] csv = msg.split(",");
    return new PlaySocketEvent(csv[0], Integer.valueOf(csv[1]), Integer.valueOf(csv[2]));
  }
}
