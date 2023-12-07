package cs3500.pa04.model;

import static org.junit.jupiter.api.Assertions.*;

import cs3500.pa04.client.model.Board;
import cs3500.pa04.client.model.CoordStatus;
import cs3500.pa04.client.model.Ship;
import cs3500.pa04.client.model.ShipType;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * test for board class
 */
class BoardTest {
  Board b1;
  Board b2;

  @BeforeEach
  void setUp() {
    List<Ship> ships = new ArrayList<>();
    Ship battleship = new Ship(ShipType.Battleship);
    Ship carrier = new Ship(ShipType.Carrier);
    Ship destroyer = new Ship(ShipType.Destroyer);
    Ship submarine = new Ship(ShipType.Submarine);
    battleship.generateHorizontal(10, 10, new Random());
    carrier.generateHorizontal(10, 10, new Random());
    destroyer.generateVertical(10, 10, new Random());
    submarine.generateHorizontal(10, 10, new Random());

    ships.add(battleship);
    ships.add(carrier);
    ships.add(destroyer);
    ships.add(submarine);
    b1 = new Board(10, 10);
    b1.generateBoard();
    b1.setShips(ships);
    b2 = new Board(6, 6);
  }

  /**
   * test for constructor throwing exception
   */
  @Test
  void testException() {
    assertThrows(IllegalArgumentException.class, () -> new Board(16, 10));
    assertThrows(IllegalArgumentException.class, () -> new Board(10, 16));
    assertThrows(IllegalArgumentException.class, () -> new Board(2, 10));
    assertThrows(IllegalArgumentException.class, () -> new Board(10, 2));
  }

  /**
   * test for generateBoard method
   */
  @Test
  void generateBoard() {
    assertEquals(b2.getBoard().size(), 0);
    b2.generateBoard();
    assertEquals(b2.getBoard().size(), 6);
    assertEquals(b2.getBoard().get(0).size(), 6);
  }

  /**
   * test for getBoard method
   */
  @Test
  void getBoard() {
    assertEquals(b1.getBoard().size(), 10);
    assertEquals(b2.getBoard().size(), 0);
  }

  /**
   * test for setShips method
   */
  @Test
  void setShips() {
    assertEquals(b2.getShips(), new ArrayList<>());
    List<Ship> ships = new ArrayList<>();
    ships.add(new Ship(ShipType.Carrier));
    ships.add(new Ship(ShipType.Carrier));
    b2.setShips(ships);
    assertEquals(b2.getShips(), ships);
  }

  /**
   * test for hasShips method
   */
  @Test
  void hasShips() {
    assertFalse(b2.hasShips());
    assertTrue(b1.hasShips());
  }

  /**
   * test getWidth method
   */
  @Test
  void getWidth() {
    assertEquals(b1.getWidth(), 10);
    assertEquals(b2.getWidth(), 6);
  }

  /**
   * test countShotsLeft method
   */
  @Test
  void countShotsLeft() {
    assertEquals(b1.countShotsLeft(), 100);
    b1.getBoard().get(0).get(0).setStatus(CoordStatus.Hit);
    b1.getBoard().get(0).get(1).setStatus(CoordStatus.Miss);
    assertEquals(b1.countShotsLeft(), 98);
    assertEquals(b2.countShotsLeft(), 36);
  }

  /**
   * test getheight method
   */
  @Test
  void getHeight() {
    assertEquals(b1.getHeight(), 10);
    assertEquals(b2.getHeight(), 6);
  }

}