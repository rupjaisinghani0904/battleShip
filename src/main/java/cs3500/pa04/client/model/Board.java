package cs3500.pa04.client.model;

import java.util.ArrayList;
import java.util.List;

/**
 * represents a Board in BattleSalvo
 * handles placing ships and updating board state
 */
public class Board {
  private List<Ship> ships;
  private final List<List<Coord>> board;
  private int height;
  private int width;

  /**
   * creates a new instance of Board with given ships and dimensions
   *
   * @param height - height of board inputted by user
   * @param width  - width of board inputted by user
   */
  public Board(int height, int width) throws IllegalArgumentException {
    if (height > 15 || height < 6) {
      throw new IllegalArgumentException("Height must be between 6 and 10");
    } else if (width > 15 || width < 6) {
      throw new IllegalArgumentException("Width must be between 6 and 10");
    } else {
      this.ships = new ArrayList<>();
      this.board = new ArrayList<>();
      this.height = height;
      this.width = width;
    }
  }

  /**
   * generates 2d list given list of ships and dimensions
   */
  public void generateBoard() {
    for (int y = 0; y < height; y++) {
      board.add(new ArrayList<>());
      for (int x = 0; x < width; x++) {
        board.get(y).add(new Coord(x, y, CoordStatus.Empty));
      }
    }

    for (Ship s : ships) {
      for (Coord c : s.getCoordinates()) {
        board.get(c.getYPosn()).get(c.getXPosn()).setStatus(CoordStatus.Ship);
      }
    }
  }

  /**
   * getter for board field
   *
   * @return board field
   */
  public List<List<Coord>> getBoard() {
    return board;
  }

  /**
   * sets ships field to given list of ships
   *
   * @param ships list of ships to set field to
   */
  public void setShips(List<Ship> ships) {
    for (Ship s : ships) {
      for (Coord c : s.getCoordinates()) {
        this.board.get(c.getYPosn()).get(c.getXPosn()).setStatus(CoordStatus.Ship);
      }
    }
    this.ships = ships;
  }

  /**
   * determines if board still contains ships
   *
   * @return true if does, false if not
   */
  public boolean hasShips() {
    return ships.size() > 0;
  }

  /**
   * getter for ships field
   *
   * @return ships field
   */
  public List<Ship> getShips() {
    return ships;
  }

  /**
   * getter for width field
   *
   * @return width of board
   */
  public int getWidth() {
    return width;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  /**
   * calculates number of coordinates yet to be shot at
   *
   * @return number of coordinates left to be shot at
   */
  public int countShotsLeft() {
    int count = 0;
    for (List<Coord> row : board) {
      for (Coord c : row) {
        if (c.getStatus().equals(CoordStatus.Hit)
            || c.getStatus().equals(CoordStatus.Miss)) {
          count++;
        }
      }
    }
    return height * width - count;
  }

  /**
   * getter for height field
   *
   * @return height field
   */
  public int getHeight() {
    return height;
  }

}
