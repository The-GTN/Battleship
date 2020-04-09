package battleship.board;
import battleship.util.Answer;
import battleship.ship.Ship;


/**
 * A cell of the Battleship game
 *
 * @author <a href="mailto:antoine.nollet.etu@univ-lille.fr">Nollet Antoine</a>
 * @version 1.0
 */
public class Cell{

  /* the ship that this cell is supporting*/
  private Ship ship;
  /* Tihs cell as been shot ?*/
  private boolean shot;

  /**
  * Create a new empty Cell
  */
  public Cell() {
    this.shot = false;
    this.ship = null;
  }

  /**
  * Return if this cell has already been shot or not
  * @return if this cell has already been shot or not
  */
  public boolean hasBeenShot() {
    return this.shot;
  }

  /**
  * Put a ship in this cell
  * @param ship the ship we want to put in this cell
  */
  public void setShip(Ship ship) {
    this.ship = ship;
  }

  /**
  * Return the ship in this cell
  * @return the ship of this cell
  */
  public Ship getShip() {
    return this.ship;
  }

  /**
  * Return if this cell is empty
  * @return if this cell is empty
  */
  public boolean isEmpty() {
    return this.ship == null;
  }

  /**
  * Shoot the cell
  * @return an Answer : HIT, SUNK or MISSED
  */
  public Answer shoot() {
    if (this.isEmpty() || this.hasBeenShot()) {
      this.shot = true;
      return Answer.MISSED;
    }
    else {
      this.shot = true;
      this.getShip().hit();
      if (this.getShip().isSunk()) {
        return Answer.SUNK;
      }
      else {
        return Answer.HIT;
      }
    }
  }

  /**
  * Display a character for this cell
  * @param defender True if the player is the defender, False otherwise
  * @return the character of the cell
  */
  public char getCharacter(boolean defender){
    if (defender) {
      if (this.isEmpty()) {
        return '~';
      }
      else if (this.hasBeenShot()) {
        return '*';
      }
      else {
        return 'B';
      }
    }
    else {
      if (!this.hasBeenShot()) {
        return '.';
      }
      else if (this.isEmpty()) {
        return '~';
      }
      else {
        return '*';
      }
    }
  }


}
