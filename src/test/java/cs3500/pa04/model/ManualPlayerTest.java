package cs3500.pa04.model;

import static org.junit.jupiter.api.Assertions.*;

import cs3500.pa04.client.model.Board;
import cs3500.pa04.client.model.Coord;
import cs3500.pa04.client.model.CoordStatus;
import cs3500.pa04.client.model.ManualPlayer;
import cs3500.pa04.client.model.Player;
import cs3500.pa04.client.model.PlayerDataModel;
import cs3500.pa04.client.model.ShipType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * test for ManualPlayer class
 */
class ManualPlayerTest {
  Player p1;
  Player p2;
  PlayerDataModel pdm1;
  PlayerDataModel pdm2;
  Map<ShipType, Integer> spec;

  @BeforeEach
  void setUp() {
    Board b1 = new Board(6, 6);
    Board b2 = new Board(6, 6);
    pdm1 = new PlayerDataModel(b1, b2);
    p1 = new ManualPlayer("P1", pdm1, new Random());

    pdm2 = new PlayerDataModel(b2, b1);
    p2 = new ManualPlayer("P2", pdm2, new Random());

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
    assertEquals(p1.name(), "P1");
    assertEquals(p2.name(), "P2");
  }

  /**
   * test for setup method
   */
  @Test
  void setup() {
    assertEquals(pdm1.getPlayerBoard().getShips().size(), 0);
    //pdm1.getPlayerBoard().setShips(p1.setup(6, 6, spec));
    assertEquals(pdm1.getPlayerBoard().getShips().size(), 0);
  }

  /**
   * test for all exceptions in class
   */
  @Test
  void testExceptions() {
    spec.put(ShipType.Carrier, 5);
    Exception exception = assertThrows(IllegalArgumentException.class, () ->
        p1.setup(6, 6, spec));
    String expectedMessage = "Fleet size cannot exceed 6 and must contain 1 of each ship type";
    String actualMessage = exception.getMessage();
    assertTrue(actualMessage.equals(expectedMessage));

    spec.put(ShipType.Carrier, 1);
    pdm1.getOppBoard().generateBoard();
    p1.setup(6, 6, spec);
    List<Coord> shots = new ArrayList<>();
    shots.add(new Coord(-1, 0, CoordStatus.Shot));
    shots.add(new Coord(1, 0, CoordStatus.Shot));
    shots.add(new Coord(2, 0, CoordStatus.Shot));
    shots.add(new Coord(3, 0, CoordStatus.Shot));
    pdm1.setShots(shots);
    Exception exception1 = assertThrows(IllegalArgumentException.class, () -> p1.takeShots());
    String expectedMessage1 = "Shots must fit in dimensions of board";
    String actualMessage1 = exception1.getMessage();
    assertTrue(actualMessage1.equals(expectedMessage1));

    shots.remove(0);
    shots.add(new Coord(7, 0, CoordStatus.Shot));
    pdm1.setShots(shots);
    Exception exception8 = assertThrows(IllegalArgumentException.class, () -> p1.takeShots());
    String expectedMessage8 = "Shots must fit in dimensions of board";
    String actualMessage8 = exception8.getMessage();
    assertTrue(actualMessage8.equals(expectedMessage8));

    shots.remove(3);
    shots.add(new Coord(0, -1, CoordStatus.Shot));
    pdm1.setShots(shots);
    Exception exception9 = assertThrows(IllegalArgumentException.class, () -> p1.takeShots());
    String expectedMessage9 = "Shots must fit in dimensions of board";
    String actualMessage9 = exception9.getMessage();
    assertTrue(actualMessage9.equals(expectedMessage9));

    shots.remove(3);
    shots.add(new Coord(0, 7, CoordStatus.Shot));
    pdm1.setShots(shots);
    Exception exception10 = assertThrows(IllegalArgumentException.class, () -> p1.takeShots());
    String expectedMessage10 = "Shots must fit in dimensions of board";
    String actualMessage10 = exception10.getMessage();
    assertTrue(actualMessage10.equals(expectedMessage10));

    shots.remove(3);
    shots.add(new Coord(0, 0, CoordStatus.Shot));
    pdm1.setShots(shots);
    pdm1.getOppBoard().getBoard().get(0).get(0).setStatus(CoordStatus.Hit);
    Exception exception2 = assertThrows(IllegalArgumentException.class, () -> p1.takeShots());
    String expectedMessage2 = "Cannot shoot at coordinates that have already been shot at";
    String actualMessage2 = exception2.getMessage();
    assertTrue(actualMessage2.equals(expectedMessage2));

    pdm1.getOppBoard().getBoard().get(0).get(0).setStatus(CoordStatus.Miss);
    Exception exception3 = assertThrows(IllegalArgumentException.class, () -> p1.takeShots());
    String expectedMessage3 = "Cannot shoot at coordinates that have already been shot at";
    String actualMessage3 = exception3.getMessage();
    assertTrue(actualMessage3.equals(expectedMessage3));

    spec.put(ShipType.Carrier, 0);
    spec.put(ShipType.Battleship, 1);
    spec.put(ShipType.Destroyer, 1);
    spec.put(ShipType.Submarine, 1);
    Exception exception4 = assertThrows(IllegalArgumentException.class, () ->
        p1.setup(6, 6, spec));
    String expectedMessage4 = "Fleet size cannot exceed 6 and must contain 1 of each ship type";
    String actualMessage4 = exception4.getMessage();
    assertTrue(actualMessage4.equals(expectedMessage4));

    spec.put(ShipType.Carrier, 1);
    spec.put(ShipType.Battleship, 0);
    Exception exception5 = assertThrows(IllegalArgumentException.class, () ->
        p1.setup(6, 6, spec));
    String expectedMessage5 = "Fleet size cannot exceed 6 and must contain 1 of each ship type";
    String actualMessage5 = exception5.getMessage();
    assertTrue(actualMessage5.equals(expectedMessage5));

    spec.put(ShipType.Battleship, 1);
    spec.put(ShipType.Destroyer, 0);
    Exception exception6 = assertThrows(IllegalArgumentException.class, () ->
        p1.setup(6, 6, spec));
    String expectedMessage6 = "Fleet size cannot exceed 6 and must contain 1 of each ship type";
    String actualMessage6 = exception6.getMessage();
    assertTrue(actualMessage6.equals(expectedMessage6));

    spec.put(ShipType.Destroyer, 1);
    spec.put(ShipType.Submarine, 0);
    Exception exception7 = assertThrows(IllegalArgumentException.class, () ->
        p1.setup(6, 6, spec));
    String expectedMessage7 = "Fleet size cannot exceed 6 and must contain 1 of each ship type";
    String actualMessage7 = exception7.getMessage();
    assertTrue(actualMessage7.equals(expectedMessage7));
  }

  /**
   * test for takeShots method
   */
  @Test
  void takeShots() {
    List<Coord> shots = new ArrayList<>();
    shots.add(new Coord(0, 0, CoordStatus.Shot));
    shots.add(new Coord(1, 0, CoordStatus.Shot));
    shots.add(new Coord(2, 0, CoordStatus.Shot));
    shots.add(new Coord(3, 0, CoordStatus.Shot));
    pdm1.setShots(shots);
    pdm1.getOppBoard().generateBoard();
    p1.setup(6, 6, spec);
    assertEquals(p1.takeShots().size(), 4);
  }

  /**
   * test for reportDamage method
   */
  @Test
  void reportDamage() {
    pdm1.getOppBoard().generateBoard();
    pdm1.getOppBoard().getBoard().get(0).get(0).setStatus(CoordStatus.Ship);
    List<Coord> shots = new ArrayList<>();
    shots.add(pdm1.getOppBoard().getBoard().get(0).get(0));
    shots.add(pdm1.getOppBoard().getBoard().get(1).get(0));
    assertEquals(pdm1.getOppBoard().getBoard().get(1).get(0).getStatus(), CoordStatus.Empty);
    assertEquals(p1.reportDamage(shots).size(), 0);
    assertEquals(pdm1.getOppBoard().getBoard().get(1).get(0).getStatus(), CoordStatus.Empty);
  }

  /**
   * test for successfulHits method
   */
  @Test
  void successfulHits() {
    pdm1.getOppBoard().generateBoard();
    pdm1.getOppBoard().getBoard().get(0).get(0).setStatus(CoordStatus.Ship);
    List<Coord> shots = new ArrayList<>();
    shots.add(pdm1.getOppBoard().getBoard().get(0).get(0));
    shots.add(pdm1.getOppBoard().getBoard().get(1).get(0));
    assertEquals(pdm1.getOppBoard().getBoard().get(0).get(0).getStatus(), CoordStatus.Ship);
    p2.successfulHits(p1.reportDamage(shots));
    assertEquals(pdm1.getOppBoard().getBoard().get(0).get(0).getStatus(), CoordStatus.Ship);
  }
}