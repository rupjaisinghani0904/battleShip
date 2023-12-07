package cs3500.pa04.client.controller;

import cs3500.pa04.client.model.AiPlayer;
import cs3500.pa04.client.model.Board;
import cs3500.pa04.client.model.Coord;
import cs3500.pa04.client.model.CoordStatus;
import cs3500.pa04.client.model.GameResult;
import cs3500.pa04.client.model.ManualPlayer;
import cs3500.pa04.client.model.Player;
import cs3500.pa04.client.model.PlayerDataModel;
import cs3500.pa04.client.model.Ship;
import cs3500.pa04.client.model.ShipType;
import cs3500.pa04.client.view.BattleSalvoView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * represents a controller for battleSalvoGame
 * Handles user input and delegates to model and view aspects
 */
public class BattleSalvoController implements Controller {
  private Player p1;
  private Player p2;
  private PlayerDataModel pdm;
  private final Appendable output;
  private final Readable input;
  private final Random rand;

  /**
   * creates a new instance of BattleSalvoController
   *
   * @param output where to display the view
   * @param input where user will input data
   * @param rand Random object to generate next int
   */
  public BattleSalvoController(Appendable output, Readable input, Random rand) {
    this.output = output;
    this.input = input;
    this.rand = rand;
  }

  /**
   * runs the application
   */
  @Override
  public void run() {
    BattleSalvoView view = new BattleSalvoView(output);
    Scanner s = new Scanner(input);
    view.write("Hello! Welcome to the OOD BattleSalvo Game!\n");
    view.write("Please enter a valid height and width below:\n"
        + "------------------------------------------------------\n");
    List<Integer> dimensions = getDimensions(s);
    getFleet(s, dimensions);
    view.displayOppBoard(pdm.getOppBoard());
    view.displayPlayerBoard(pdm.getPlayerBoard());
    GameResult result = salvoLoop(s);
    if (result.equals(GameResult.WIN)) {
      p1.endGame(result, "You destroyed all of " + p2.name() + "'s ships.");
      p2.endGame(GameResult.LOSE, p1.name() + "destroyed all of your ships.");
    } else if (result.equals(GameResult.LOSE)) {
      p2.endGame(GameResult.WIN, "You destroyed all of " + p1.name() + "'s ships.");
      p1.endGame(result, p2.name() + "destroyed all of your ships.");
    } else {
      p1.endGame(result, "You both destroyed each others ships at the same time.");
      p2.endGame(result, "You both destroyed each others ships at the same time.");
    }
    view.write(result.toString());
  }

  /**
   * gets dimension input from user
   *
   * @return user input as list of integers
   */
  private List<Integer> getDimensions(Scanner s) {
    BattleSalvoView view = new BattleSalvoView(output);
    List<Integer> dimensions = new ArrayList<>();
    while (dimensions.size() < 2) {
      dimensions.add(s.nextInt());
    }
    Board playerBoard;
    Board oppBoard;
    try {
      playerBoard = new Board(dimensions.get(0), dimensions.get(1));
      oppBoard = new Board(dimensions.get(0), dimensions.get(1));
      pdm = new PlayerDataModel(playerBoard, oppBoard);
      p1 = new ManualPlayer("Manual Player", pdm, rand);
      p2 = new AiPlayer(rand, playerBoard);
    } catch (IllegalArgumentException e) {
      view.write("Uh Oh! You've entered invalid dimensions. "
          + "Please remember that the height and width\n"
          + "of the game must be in the range (6, 10), inclusive. Try again!"
          + "\n"
          + "------------------------------------------------------\n");
      dimensions = getDimensions(s);
    }
    return dimensions;
  }

  /**
   * get fleet input from user
   *
   * @param dimensions given dimensions from user returned from getDimensions method
   */
  private void getFleet(Scanner s, List<Integer> dimensions) {
    BattleSalvoView view = new BattleSalvoView(output);
    int max = Math.min(dimensions.get(0), dimensions.get(1));
    view.write("Please enter your fleet in the order "
        + "[Carrier, Battleship, Destroyer, Submarine].\n"
        + "Remember, your fleet may not exceed size " + max + ".\n"
        + "--------------------------------------------------------------------------------\n");
    Map<ShipType, Integer> specifications = new HashMap<>();
    specifications.put(ShipType.Carrier, s.nextInt());
    specifications.put(ShipType.Battleship, s.nextInt());
    specifications.put(ShipType.Destroyer, s.nextInt());
    specifications.put(ShipType.Submarine, s.nextInt());
    List<Ship> p1Ships;
    List<Ship> p2Ships;
    try {
      p1Ships = p1.setup(dimensions.get(0), dimensions.get(1), specifications);
      p2Ships = p2.setup(dimensions.get(0), dimensions.get(1), specifications);
      pdm.getPlayerBoard().setShips(p1Ships);
      pdm.getOppBoard().setShips(p2Ships);
    } catch (IllegalArgumentException e) {
      view.write("Uh Oh! You've entered invalid fleet sizes.\n");
      getFleet(s, dimensions);
    }
  }

  /**
   * gameloop of salvo
   * handles getting player shots and displaying until game ends
   */
  private GameResult salvoLoop(Scanner s) {
    BattleSalvoView view = new BattleSalvoView(output);
    while (pdm.getPlayerBoard().hasShips() && pdm.getOppBoard().hasShips()) {
      List<Coord> playerShots;
      if (pdm.getPlayerBoard().getShips().size() > pdm.getPlayerBoard().countShotsLeft()) {
        playerShots = getPlayerShots(s, pdm.getPlayerBoard().countShotsLeft());
      } else {
        playerShots = getPlayerShots(s, pdm.getPlayerBoard().getShips().size());
      }
      List<Coord> aiShots = p2.takeShots();
      List<Coord> aiToPlayerHit = p1.reportDamage(aiShots);
      List<Coord> playerToAiHit = p2.reportDamage(playerShots);
      p1.successfulHits(playerToAiHit);
      p2.successfulHits(aiToPlayerHit);
      view.displayOppBoard(pdm.getOppBoard());
      view.displayPlayerBoard(pdm.getPlayerBoard());
    }

    if (pdm.getPlayerBoard().getShips().size() != 0
        && pdm.getOppBoard().getShips().size() == 0) {
      return GameResult.WIN;
    } else if (pdm.getPlayerBoard().getShips().size() == 0
        && pdm.getOppBoard().getShips().size() != 0) {
      return GameResult.LOSE;
    } else {
      return GameResult.DRAW;
    }
  }

  /**
   * handles getting user input for shots
   *
   * @param noOfShots number of shots user must input
   * @return list of player shots
   */
  private List<Coord> getPlayerShots(Scanner s, int noOfShots) {
    BattleSalvoView view = new BattleSalvoView(output);
    view.write("Please Enter " + noOfShots + " Shots:\n"
        + "------------------------------------------------------------------\n");
    List<Coord> playerShots = new ArrayList<>();
    while (playerShots.size() < noOfShots) {
      playerShots.add(new Coord(s.nextInt(), s.nextInt(), CoordStatus.Shot));
    }
    pdm.setShots(playerShots);
    List<Coord> shots;
    try {
      shots = p1.takeShots();
    } catch (IllegalArgumentException e) {
      view.write("Uh Oh! You've entered invalid shots.\n");
      shots = getPlayerShots(s, noOfShots);
    }
    return shots;
  }
}
