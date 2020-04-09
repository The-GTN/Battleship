package battleship.util;

/**
 * Position of a grid
 *
 * @author <a href="mailto:antoine.nollet.etu@univ-lille.fr">Nollet Antoine</a>
 * @version 1.0
 */
public class Position{

  /* position x */
  private int x;
  /* position y */
  private int y;

  /**
  * Create a Position
  */
  public Position(int x,int y) {
    if (x < 0) {
      this.x = 0;
    }
    else {
      this.x = x;
    }
    if (y < 0) {
      this.y = 0;
    }
    else {
      this.y = y;
    }
  }

  /**
  * Return the coordinate x of this Position
  * @return the coordinate x of this Position
  */
  public int getX() {
    return this.x;
  }

  /**
  * Return the coordinate y of this Position
  * @return the coordinate y of this Position
  */
  public int getY() {
    return this.y;
  }

  /**
  * Return if two Date are equals or not
  *
  * @param o the Date compared with this Date
  * @return True if the two Date are equals, False otherwise.
  */
  public boolean equals(Object o) {
    if (o instanceof Position) {
      Position other = (Position) o;
      if (this.x == other.x && this.y == other.y) {
        return true;
      }
    }
    return false;
  }

  /**
  * Return a String representation of this Position
  * @return the string representation of this Position
  */
  public String toString() {
    return "("+this.x+","+this.y+")";
  }

}
