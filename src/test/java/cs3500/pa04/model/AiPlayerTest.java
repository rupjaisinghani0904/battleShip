package cs3500.pa04.model;

import static org.junit.jupiter.api.Assertions.*;

import cs3500.pa04.client.model.AiPlayer;
import cs3500.pa04.client.model.Board;
import cs3500.pa04.client.model.Coord;
import cs3500.pa04.client.model.CoordStatus;
import cs3500.pa04.client.model.Player;
import cs3500.pa04.client.model.Ship;
import cs3500.pa04.client.model.ShipType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * test for AiPlayer class
 */
class AiPlayerTest {
  Player p1;
  Board b1;
  Player p2;
  Map<ShipType, Integer> spec;

  @BeforeEach
  void setUp() {
    b1 = new Board(6, 6);
    p1 = new AiPlayer(new Random(), b1);

    p2 = new AiPlayer(new Random(), b1);

    spec = new HashMap<>();
    spec.put(ShipType.Carrier, 1);
    spec.put(ShipType.Battleship, 1);
    spec.put(ShipType.Destroyer, 1);
    spec.put(ShipType.Submarine, 1);
  }

  /**
   * test for name method
   */
  @Test
  void name() {
    assertEquals(p1.name(), "AI Player");
  }

  /**
   * test for setup method
   */
  @Test
  void setup() {
    assertEquals(b1.getShips().size(), 0);
    b1.setShips(p1.setup(6, 6, spec));
    assertEquals(b1.getShips().size(), 4);
  }

  /**
   * test for takeShots method
   */
  @Test
  void takeShots() {
    b1.setShips(p1.setup(6, 6, spec));
    b1.generateBoard();
    assertEquals(p1.takeShots().size(), 4);
    for (List<Coord> row : b1.getBoard()) {
      for (Coord c : row) {
        c.setStatus(CoordStatus.Hit);
      }
    }
    b1.getBoard().get(0).get(0).setStatus(CoordStatus.Empty);
    List<Ship> ships = new ArrayList<>();
    ships.add(new Ship(ShipType.Carrier));
    ships.add(new Ship(ShipType.Submarine));
    b1.setShips(ships);
    assertEquals(p1.takeShots().size(), 0);
  }

  /**
   * test for reportDamage method
   */
  @Test
  void reportDamage() {
    b1.generateBoard();
    b1.getBoard().get(0).get(0).setStatus(CoordStatus.Ship);
    List<Coord> shots = new ArrayList<>();
    shots.add(b1.getBoard().get(0).get(0));
    shots.add(b1.getBoard().get(1).get(0));
    assertEquals(b1.getBoard().get(1).get(0).getStatus(), CoordStatus.Empty);
    assertEquals(p1.reportDamage(shots).size(), 0);
    assertEquals(b1.getBoard().get(1).get(0).getStatus(), CoordStatus.Empty);
  }

  /**
   * test successfulHits method
   */
  @Test
  void successfulHits() {
    b1.generateBoard();
    b1.getBoard().get(0).get(0).setStatus(CoordStatus.Ship);
    List<Coord> shots = new ArrayList<>();
    shots.add(b1.getBoard().get(0).get(0));
    shots.add(b1.getBoard().get(1).get(0));
    assertEquals(b1.getBoard().get(0).get(0).getStatus(), CoordStatus.Ship);
    p2.successfulHits(p1.reportDamage(shots));
    assertEquals(b1.getBoard().get(0).get(0).getStatus(), CoordStatus.Ship);
  }
}