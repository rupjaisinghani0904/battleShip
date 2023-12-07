package cs3500.pa04.client.model;

import java.util.ArrayList;
import java.util.List;

/**
 * represents player data that is communicated throughout the game
 */
public class PlayerDataModel {
  private final Board playerBoard;
  private final Board oppBoard;
  private List<Coord> shots;

  /**
   * creates a new instance of PlayerDateModel with given boards
   *
   * @param playerBoard - player board
   * @param oppBoard - opponents board
   */
  public PlayerDataModel(Board playerBoard, Board oppBoard) {
    this.playerBoard = playerBoard;
    this.oppBoard = oppBoard;
    shots = new ArrayList<>();
  }

  /**
   * getter for shots field
   *
   * @return shots field
   */
  public List<Coord> getShots() {
    return shots;
  }

  /**
   * getter of opponent board field
   *
   * @return oppBoard field
   */
  public Board getOppBoard() {
    return oppBoard;
  }

  public Board getPlayerBoard() {
    return playerBoard;
  }

  /**
   * setter for shots field
   *
   * @param playerShots sets shots to given shots
   */
  public void setShots(List<Coord> playerShots) {
    this.shots = playerShots;
  }
}
