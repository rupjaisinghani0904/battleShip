package cs3500.pa04.client.model;

/**
 * represents possible shipTypes and their respective sizes
 */
public enum ShipType {
  Carrier(6),
  Battleship(5),
  Destroyer(4),
  Submarine(3);

  private final int size;

  /**
   * creates a new instance of ShipType with given size
   *
   * @param size size of ship type
   */
  ShipType(final int size) {
    this.size = size;
  }

  /**
   * getter for size field
   *
   * @return size field
   */
  public int getSize() {
    return size;
  }
}
