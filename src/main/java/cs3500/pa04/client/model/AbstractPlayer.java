package cs3500.pa04.client.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * represents an Abstract player in battleSalvo game
 * used for abstraction
 */
public abstract class AbstractPlayer implements Player {
  protected final Random rand;
  protected String name;
  protected GameResult result;
  protected List<Ship> ships;
  protected Board oppBoard;

  /**
   * creates a new instance of Abstract player
   *
   * @param name name of player
   * @param rand random object
   */
  public AbstractPlayer(String name, Random rand, Board oppBoard) {
    this.name = name;
    this.rand = rand;
    this.result = GameResult.DRAW;
    this.ships = new ArrayList<>();
    this.oppBoard = oppBoard;
  }

  /**
   * gets players name
   *
   * @return name as String
   */
  public String name() {
    return name;
  }

  /**
   * Given the specifications for a BattleSalvo board, return a list of ships with their locations
   * on the board.
   *
   * @param height the height of the board, range: [6, 15] inclusive
   * @param width the width of the board, range: [6, 15] inclusive
   * @param spec a map of ship type to the number of occurrences each ship should
   *                       appear on the board
   * @return the placements of each ship on the board
   */
  public List<Ship> setup(int height, int width, Map<ShipType, Integer> spec) {
    int sum = 0;
    for (ShipType shipType : ShipType.values()) {
      sum += spec.get(shipType);
    }
    if (sum > Math.min(height, width) || spec.get(ShipType.Carrier) < 1
        || spec.get(ShipType.Battleship) < 1
        || spec.get(ShipType.Destroyer) < 1
        || spec.get(ShipType.Submarine) < 1) {
      throw new IllegalArgumentException("Fleet size cannot exceed "
          + Math.min(height, width)
          + " and must contain 1 of each ship type");
    }
    for (ShipType shipType : ShipType.values()) {
      for (int i = 0; i < spec.get(shipType); i++) {
        Ship s = new Ship(shipType);
        boolean fits = false;
        while (!fits) {
          s = new Ship(shipType);
          if (rand.nextInt(2) == 0) {
            s.generateVertical(height, width, rand);
          } else {
            s.generateHorizontal(height, width, rand);
          }
          fits = checkShipFits(s);
        }
        s.setStart();
        ships.add(s);
      }
    }
    oppBoard.setHeight(height);
    oppBoard.setWidth(width);
    oppBoard.generateBoard();
    return ships;
  }

  /**
   * determines if given ship fits on board
   *
   * @param ship ship to check if fits
   * @return true if fits, false if not
   */
  private boolean checkShipFits(Ship ship) {
    boolean fits = true;
    for (Coord c : ship.getCoordinates()) {
      for (Ship s : ships) {
        for (Coord coord : s.getCoordinates()) {
          if (c.equals(coord)) {
            fits = false;
            break;
          }
        }
      }
    }
    return fits;
  }

  /**
   * Returns this player's shots on the opponent's board. The number of shots returned should
   * equal the number of ships on this player's board that have not sunk.
   *
   * @return the locations of shots on the opponent's board
   */
  public abstract List<Coord> takeShots();

  /**
   * Given the list of shots the opponent has fired on this player's board, report which
   * shots hit a ship on this player's board.
   *
   * @param opponentShotsOnBoard the opponent's shots on this player's board
   * @return a filtered list of the given shots that contain all locations of shots that hit a
   * ship on this board
   */
  public List<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {
    List<Coord> successfulHits = new ArrayList<>();
    for (Coord c : opponentShotsOnBoard) {
      for (Ship s : ships) {
        for (Coord coord : s.getCoordinates()) {
          if (c.equals(coord)) {
            coord.setStatus(CoordStatus.Hit);
            successfulHits.add(c);
          } else {
            c.setStatus(CoordStatus.Miss);
          }
        }
      }
    }
    removeSunk();
    return successfulHits;
  }

  /**
   * removes all sunk ships from board
   */
  public void removeSunk() {
    List<Ship> sunkShips = new ArrayList<>();
    for (Ship s : ships) {
      if (s.sunk()) {
        sunkShips.add(s);
      }
    }
    ships.removeAll(sunkShips);
  }

  /**
   * Reports to this player what shots in their previous volley returned from takeShots()
   * successfully hit an opponent's ship.
   *
   * @param shotsThatHitOpponentShips the list of shots that successfully hit the opponent's ships
   */
  public abstract void successfulHits(List<Coord> shotsThatHitOpponentShips);

  /**
   * Notifies the player that the game is over.
   * Win, lose, and draw should all be supported
   *
   * @param result if the player has won, lost, or forced a draw
   * @param reason the reason for the game ending
   */
  public void endGame(GameResult result, String reason) {
    this.result = result;
  }
}
