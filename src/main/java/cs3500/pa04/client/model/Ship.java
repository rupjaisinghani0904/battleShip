package cs3500.pa04.client.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * represents a ship in the battlesalvo game
 */
public class Ship {
  private final ShipType type;
  private final List<Coord> coordinates;
  private String shipDirection = "";
  private Coord start;

  /**
   * creates a new instance with given type and coordinates
   *
   * @param type - one of possible shiptype enumerations
   */
  public Ship(ShipType type) {
    this.type = type;
    this.coordinates = new ArrayList<>();
  }

  /**
   * setter for the starting ship coord
   */
  public void setStart() {
    this.start = coordinates.get(0);
  }

  /**
   * getter for the starting ship coord
   *
   * @return starting coord
   */
  public Coord getStart() {
    return start;
  }

  /**
   * getter for coordinates field
   *
   * @return coordinates field
   */
  public List<Coord> getCoordinates() {
    return coordinates;
  }

  /**
   * generates a vertical ship
   *
   * @param height height of board
   * @param width width of board
   */
  public void generateVertical(int height, int width, Random rand) {
    int startX = rand.nextInt(width);
    int startY = rand.nextInt(height - type.getSize() + 1);
    shipDirection = "VERTICAL";
    for (int i = 0; i < type.getSize(); i++) {
      coordinates.add(new Coord(startX, startY + i, CoordStatus.Ship));
    }
  }

  /**
   * generates a horizontal ship
   *
   * @param height height of board
   * @param width width of board
   */
  public void generateHorizontal(int height, int width, Random rand) {
    int startX = rand.nextInt(width - type.getSize() + 1);
    int startY = rand.nextInt(height);
    shipDirection = "HORIZONTAL";
    for (int i = 0; i < type.getSize(); i++) {
      coordinates.add(new Coord(startX + i, startY, CoordStatus.Ship));
    }
  }

  /**
   * determines if a ship is sunk
   *
   * @return true if all coordinates have status of 'Hit'
   */
  public boolean sunk() {
    boolean sunk = true;
    for (Coord c : coordinates) {
      sunk = sunk && c.getStatus().equals(CoordStatus.Hit);
    }
    return sunk;
  }

  /**
   * getter for the direction of the ship
   *
   * @return String rep of a ship direction
   */
  public String getShipDirection() {
    return shipDirection;
  }
}
