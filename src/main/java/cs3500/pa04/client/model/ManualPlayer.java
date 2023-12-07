package cs3500.pa04.client.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * represents a manual player in battleSalvo game
 * decisions made by user input
 */
public class ManualPlayer extends AbstractPlayer {
  private final PlayerDataModel pdm;

  /**
   * creates a new instance of manualPlayer
   *
   * @param name name of player
   * @param pdm player data model containing information to keep track of
   * @param rand Random object to generate next int from
   */
  public ManualPlayer(String name, PlayerDataModel pdm, Random rand) {
    super(name, rand, pdm.getOppBoard());
    this.pdm = pdm;
  }

  /**
   * fires player shots to opponent
   *
   * @return players shots as list of coordinates
   */
  @Override
  public List<Coord> takeShots() {
    List<Coord> newShots = new ArrayList<>();
    for (Coord c : pdm.getShots()) {
      if (c.getXPosn() < 0 || c.getXPosn() > pdm.getOppBoard().getWidth()
          || c.getYPosn() < 0 || c.getYPosn() > pdm.getOppBoard().getHeight()) {
        throw new IllegalArgumentException("Shots must fit in dimensions of board");
      }
      Coord shot = pdm.getOppBoard().getBoard().get(c.getYPosn()).get(c.getXPosn());
      if (shot.getStatus().equals(CoordStatus.Hit)
          || shot.getStatus().equals(CoordStatus.Miss)) {
        throw new IllegalArgumentException("Cannot shoot at coordinates "
            + "that have already been shot at");
      } else {
        newShots.add(shot);
      }
    }
    return newShots;
  }

  /**
   * updates view of opponent board to reflect shots that hit ships or missed
   *
   * @param shotsThatHitOpponentShips the list of shots that successfully hit the opponent's ships
   */
  @Override
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {
    for (Coord c : shotsThatHitOpponentShips) {
      c.setStatus(CoordStatus.Hit);
    }
  }
}
