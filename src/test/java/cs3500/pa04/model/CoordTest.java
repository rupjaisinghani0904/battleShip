package cs3500.pa04.model;

import static org.junit.jupiter.api.Assertions.*;

import cs3500.pa04.client.model.Coord;
import cs3500.pa04.client.model.CoordStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * test for Coord class
 */
class CoordTest {
  Coord c1;
  Coord c2;
  Coord c3;

  @BeforeEach
  void setUp() {
    c1 = new Coord(0, 0, CoordStatus.Ship);
    c2 = new Coord(0, 1, CoordStatus.Ship);
    c3 = new Coord(0, 0, CoordStatus.Shot);
  }

  /**
   * test for getXPosn method
   */
  @Test
  void getX() {
    assertEquals(c1.getXPosn(), 0);
    assertEquals(c2.getXPosn(), 0);
    assertEquals(c3.getXPosn(), 0);
  }

  /**
   * test for getYPosn method
   */
  @Test
  void getY() {
    assertEquals(c1.getYPosn(), 0);
    assertEquals(c2.getYPosn(), 1);
    assertEquals(c3.getYPosn(), 0);
  }

  /**
   * test for setStatus method
   */
  @Test
  void setStatus() {
    assertEquals(c1.getStatus(), CoordStatus.Ship);
    c1.setStatus(CoordStatus.Hit);
    assertEquals(c1.getStatus(), CoordStatus.Hit);
  }

  /**
   * test for equals method
   */
  @Test
  void testEquals() {
    assertTrue(c1.equals(c3));
    assertFalse(c1.equals(c2));
    assertFalse(c2.equals("Hello"));
  }

  /**
   * tests for getstatus method
   */
  @Test
  void getStatus() {
    assertEquals(c1.getStatus(), CoordStatus.Ship);
    assertEquals(c2.getStatus(), CoordStatus.Ship);
    assertEquals(c3.getStatus(), CoordStatus.Shot);
  }
}