import org.junit.*;
import static org.junit.Assert.*;

import battleship.util.Answer;
import battleship.ship.*;
import battleship.board.Cell;


public class TestCell {

  @Test
  public void decreaseHP() {
    Cell cell = new Cell();
    Ship ship = new Ship(ModelShip.TORPILLEUR);
    cell.setShip(ship);
    assertEquals(2,ship.getHP());
    cell.shoot();
    assertEquals(1,ship.getHP());
  }

  @Test
  public void testMissed() {
    Cell cellEmpty = new Cell();
    assertTrue(cellEmpty.isEmpty());
    assertEquals(Answer.MISSED,cellEmpty.shoot());
  }

  @Test
  public void testHit() {
    Cell cell = new Cell();
    Ship ship = new Ship(ModelShip.TORPILLEUR);
    cell.setShip(ship);
    assertEquals(Answer.HIT,cell.shoot());
  }

  @Test
  public void testSunk() {
    Cell cell = new Cell();
    Ship ship = new Ship(ModelShip.TORPILLEUR);
    cell.setShip(ship);
    Cell cell2 = new Cell();
    cell2.setShip(ship);
    cell.shoot();
    assertEquals(1,ship.getHP());
    assertEquals(Answer.SUNK,cell2.shoot());

  }

  @Test
  public void notShootTwiceInTheSameCell() {
    Cell cell = new Cell();
    Ship ship = new Ship(ModelShip.TORPILLEUR);
    cell.setShip(ship);
    assertFalse(cell.hasBeenShot());
    assertEquals(Answer.HIT,cell.shoot());
    assertTrue(cell.hasBeenShot());
    assertEquals(Answer.MISSED,cell.shoot());
  }

  @Test
  public void CellDefender() {
    Cell cell = new Cell();
    Cell cell2 = new Cell();
    Cell cellEmpty = new Cell();
    Ship ship = new Ship(ModelShip.TORPILLEUR);
    cell.setShip(ship);
    cell2.setShip(ship);
    cell2.shoot();
    assertEquals('B',cell.getCharacter(true));
    assertEquals('*',cell2.getCharacter(true));
    assertEquals('~',cellEmpty.getCharacter(true));
  }

  @Test
  public void CellOpponent() {
    Cell cell = new Cell();
    Cell cell2 = new Cell();
    Cell cellEmpty = new Cell();
    Ship ship = new Ship(ModelShip.TORPILLEUR);
    cell.setShip(ship);
    cell2.setShip(ship);
    cell2.shoot();
    cellEmpty.shoot();
    assertEquals('.',cell.getCharacter(false));
    assertEquals('*',cell2.getCharacter(false));
    assertEquals('~',cellEmpty.getCharacter(false));
  }

  // ---Pour permettre l'exécution des test----------------------
  public static junit.framework.Test suite() {
      return new junit.framework.JUnit4TestAdapter(TestCell.class);
  }
}
