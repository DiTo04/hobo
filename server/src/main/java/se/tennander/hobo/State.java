package se.tennander.hobo;

import java.util.List;

import org.jetbrains.annotations.NotNull;

public class State {
  public List<List<Tile>> tiles;
  public PlayerMark turn;
  public PlayerMark winner;
  public PlayerMark player;

  private State(List<List<Tile>> tiles, PlayerMark turn, PlayerMark winner, PlayerMark player) {
    this.tiles = tiles;
    this.turn = turn;
    this.winner = winner;
    this.player = player;
  }

  public static State newGame() {
    return new State(
        getEmptyTiles(),
        PlayerMark.X,
        PlayerMark.Empty,
        PlayerMark.X
    );
  }

  @NotNull
  private static List<List<Tile>> getEmptyTiles() {
    return List.of(getEmptyRow(0), getEmptyRow(1), getEmptyRow(2));
  }

  @NotNull
  private static List<Tile> getEmptyRow(int x) {
    return List.of(Tile.empty(x,0), Tile.empty(x,1), Tile.empty(x,2));
  }

  public static class Tile {
    public int x;
    public int y;
    public PlayerMark marker;

    Tile(int x, int y, PlayerMark marker) {
      this.x = x;
      this.y = y;
      this.marker = marker;
    }

    public static Tile empty(int x, int y) {
      return new Tile(x, y, PlayerMark.Empty);
    }
  }

  public enum  PlayerMark {
    X, O, Empty
  }
}
