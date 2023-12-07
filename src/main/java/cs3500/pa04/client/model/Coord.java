package cs3500.pa04.client.model;

/**
 * represents a coordinate on the battlesalvo board
 */
public class Coord {
  private final int xPosn;
  private final int yPosn;
  private CoordStatus status;

  /**
   * creates a new instance of Coord
   *
   * @param xPosn - xPosn value of coordinate
   * @param yPosn - yPosn value of coordinate
   * @param status - one of CoordStatus enum tyPosnpes
   */
  public Coord(int xPosn, int yPosn, CoordStatus status) {
    this.xPosn = xPosn;
    this.yPosn = yPosn;
    this.status = status;
  }

  /**
   * getter for xPosn field
   *
   * @return xPosn field
   */
  public int getXPosn() {
    return xPosn;
  }

  /**
   * getter for yPosn field
   *
   * @return yPosn field
   */
  public int getYPosn() {
    return yPosn;
  }

  /**
   * setter for status field
   *
   * @param status given status for field to be set to
   */
  public void setStatus(CoordStatus status) {
    this.status = status;
  }

  /**
   * determines if given object and this coord are equivalent
   *
   * @param o - object to compare to this
   * @return true if equal, false if not
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof Coord) {
      Coord c = (Coord) o;
      return this.xPosn == c.xPosn
          && this.yPosn == c.yPosn;
    } else {
      return false;
    }
  }

  /**
   * getter for status field
   *
   * @return status field
   */
  public CoordStatus getStatus() {
    return status;
  }
}
