package cs3500.pa04.client.view;

import cs3500.pa04.client.model.Board;
import cs3500.pa04.client.model.Coord;
import cs3500.pa04.client.model.CoordStatus;
import java.io.IOException;
import java.util.List;

/**
 * represents view component of application
 */
public class BattleSalvoView {
  private final Appendable appendable;

  /**
   * creates a new instance of BattleSalvoView
   *
   * @param appendable appendable to output to
   */
  public BattleSalvoView(Appendable appendable) {
    this.appendable = appendable;
  }

  /**
   * writes to appendable field
   *
   * @param s string to write
   */
  public void write(String s) {
    try {
      appendable.append(s);
    } catch (IOException e) {
      System.out.println("failed to connect to console view");
    }
  }

  /**
   * displays player board
   *
   * @param board players board to be displayed
   */
  public void displayPlayerBoard(Board board) {
    write("Player Board:\n");
    for (List<Coord> row : board.getBoard()) {
      for (Coord c : row) {
        if (c.getStatus().equals(CoordStatus.Empty)) {
          write("_ ");
        } else if (c.getStatus().equals(CoordStatus.Ship)) {
          write("S ");
        } else if (c.getStatus().equals(CoordStatus.Hit)) {
          write("H ");
        } else {
          write("_ ");
        }
      }
      write("\n");
    }
  }

  /**
   * displays opponent board
   *
   * @param board opponent board to be displayed
   */
  public void displayOppBoard(Board board) {
    write("Opponent Board:\n");
    for (List<Coord> row : board.getBoard()) {
      for (Coord c : row) {
        if (c.getStatus().equals(CoordStatus.Empty)) {
          write("_ ");
        } else if (c.getStatus().equals(CoordStatus.Ship)) {
          write("_ ");
        } else if (c.getStatus().equals(CoordStatus.Hit)) {
          write("H ");
        } else {
          write("M ");
        }
      }
      write("\n");
    }
  }
}
