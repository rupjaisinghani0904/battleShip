package cs3500.pa04.view;

import static org.junit.jupiter.api.Assertions.*;

import cs3500.pa04.client.model.Board;
import cs3500.pa04.client.model.CoordStatus;
import cs3500.pa04.client.view.BattleSalvoView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * test for BattleSalvoView class
 */
class BattleSalvoViewTest {
  StringBuilder sb;
  BattleSalvoView view;

  @BeforeEach
  void setUp() {
    sb = new StringBuilder();
    view = new BattleSalvoView(sb);
  }

  /**
   * test for write method
   */
  @Test
  void write() {
    view.write("Hello");
    assertEquals(sb.toString(), "Hello");
  }

  /**
   * test for displayPlayerBoard method
   */
  @Test
  void displayPlayerBoard() {
    Board b = new Board(6, 6);
    b.generateBoard();
    b.getBoard().get(0).get(0).setStatus(CoordStatus.Ship);
    b.getBoard().get(0).get(1).setStatus(CoordStatus.Hit);
    b.getBoard().get(0).get(2).setStatus(CoordStatus.Miss);
    view.displayPlayerBoard(b);
    String expected = "Player Board:\n"
        + "S H _ _ _ _ \n"
        + "_ _ _ _ _ _ \n"
        + "_ _ _ _ _ _ \n"
        + "_ _ _ _ _ _ \n"
        + "_ _ _ _ _ _ \n"
        + "_ _ _ _ _ _ \n";
    assertEquals(sb.toString(), expected);
  }

  /**
   * test for displayOppBoard method
   */
  @Test
  void displayOppBoard() {
    Board b = new Board(6, 6);
    b.generateBoard();
    b.getBoard().get(0).get(0).setStatus(CoordStatus.Ship);
    b.getBoard().get(0).get(1).setStatus(CoordStatus.Hit);
    b.getBoard().get(0).get(2).setStatus(CoordStatus.Miss);
    view.displayOppBoard(b);
    String expected = "Opponent Board:\n"
        + "_ H M _ _ _ \n"
        + "_ _ _ _ _ _ \n"
        + "_ _ _ _ _ _ \n"
        + "_ _ _ _ _ _ \n"
        + "_ _ _ _ _ _ \n"
        + "_ _ _ _ _ _ \n";
    assertEquals(sb.toString(), expected);
  }
}