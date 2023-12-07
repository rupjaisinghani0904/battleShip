package cs3500.pa04.model;

import static org.junit.jupiter.api.Assertions.*;

import cs3500.pa04.client.model.Coord;
import cs3500.pa04.client.model.CoordStatus;
import cs3500.pa04.client.model.Ship;
import cs3500.pa04.client.model.ShipType;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * test for Ship class
 */
class ShipTest {
  Ship s;
  Ship ship;

  @BeforeEach
  void setUp() {
    s = new Ship(ShipType.Battleship);
    s.generateVertical(10, 10, new Random());
    ship = new Ship(ShipType.Carrier);
  }

  /**
   * test for getCoordinates method
   */
  @Test
  void getCoordinates() {
    assertEquals(0, ship.getCoordinates().size());
    assertEquals(5, s.getCoordinates().size());
  }

  /**
   * test for generateVertical method
   */
  @Test
  void generateVertical() {
    assertEquals(0, ship.getCoordinates().size());
    ship.generateVertical(10, 10, new Random());
    assertEquals(6, ship.getCoordinates().size());
    assertEquals(5, s.getCoordinates().size());
  }

  /**
   * test for generateHorizontal class
   */
  @Test
  void generateHorizontal() {
    assertEquals(0, ship.getCoordinates().size());
    ship.generateHorizontal(10, 10, new Random());
    assertEquals(6, ship.getCoordinates().size());
  }

  /**
   * test for sunk method
   */
  @Test
  void sunk() {
    assertEquals(s.sunk(), false);
    for (Coord c : s.getCoordinates()) {
      c.setStatus(CoordStatus.Hit);
    }
    assertEquals(s.sunk(), true);
  }
}