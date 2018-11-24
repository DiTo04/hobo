package se.tennander.hobo;

import java.util.List;

import org.jetbrains.annotations.NotNull;

public class State {
  public List<List<Tile>> tiles;
  public PlayerMark turn;
  public PlayerMark winner;

  private State(List<List<Tile>> tiles, PlayerMark turn, PlayerMark winner) {
    this.tiles = tiles;
    this.turn = turn;
    this.winner = winner;
  }

  public static State newGame() {
    return new State(
        getEmptyTiles(),
        PlayerMark.X,
        PlayerMark.Empty
    );
  }

  @NotNull
  private static List<List<Tile>> getEmptyTiles() {
    return List.of(getEmptyRow(-1), getEmptyRow(0), getEmptyRow(1));
  }

  @NotNull
  private static List<Tile> getEmptyRow(int x) {
    return List.of(Tile.empty(x,-1), Tile.empty(x,0), Tile.empty(x,1));
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
