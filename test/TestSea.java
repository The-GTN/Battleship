import org.junit.*;
import static org.junit.Assert.*;

import battleship.util.*;
import battleship.ship.*;
import battleship.board.*;


public class TestSea {

  @Test(expected = ArrayIndexOutOfBoundsException.class)
  public void notValidPositionToShoot() {
    Sea sea = new Sea(5,5);
    Position attack = new Position(6,5);
    sea.shoot(attack);
  }

  @Test(expected = IllegalStateException.class)
  public void notValidPositionToAddShipHorizontally() {
    Sea sea = new Sea(5,5);
    Ship ship = new Ship(ModelShip.TORPILLEUR);
    Position pos = new Position(5,0);
    sea.addShipHorizontally(ship,pos);
  }

  @Test(expected = IllegalStateException.class)
  public void notValidPositionToAddShipVertically() {
    Sea sea = new Sea(5,5);
    Ship ship = new Ship(ModelShip.TORPILLEUR);
    Position pos = new Position(0,5);
    sea.addShipVertically(ship,pos);
  }

  @Test
  public void testShoot() {
    Sea sea = new Sea(5,5);
    Ship ship = new Ship(ModelShip.TORPILLEUR);
    Position pos = new Position(0,0);
    Position pos2 = new Position(0,1);
    Position pos3 = new Position(4,4);
    sea.addShipVertically(ship,pos);
    assertEquals(2,sea.getLife());
    assertEquals(1,sea.getShips());
    sea.shoot(pos3);
    assertEquals(2,sea.getLife());
    assertEquals(1,sea.getShips());
    sea.shoot(pos);
    assertEquals(1,sea.getLife());
    assertEquals(1,sea.getShips());
    sea.shoot(pos2);
    assertEquals(0,sea.getLife());
    assertEquals(0,sea.getShips());
  }

  @Test
  public void testAddShipRandomly() {
    Sea sea = new Sea(2,1);
    Ship ship = new Ship(ModelShip.TORPILLEUR);
    assertEquals(0,sea.getLife());
    assertEquals(0,sea.getShips());
    sea.AddShipRandomly(ship);
    assertEquals(2,sea.getLife());
    assertEquals(1,sea.getShips());
  }

  @Test
  public void testGetCell() {
    Sea sea = new Sea(1,1);
    Position pos = new Position(0,0);
    Cell cell = sea.getCell(pos);
    Cell cell2 = sea.getCell(pos);
    assertSame(cell,cell2);
  }



  // ---Pour permettre l'exécution des test----------------------
  public static junit.framework.Test suite() {
      return new junit.framework.JUnit4TestAdapter(TestSea.class);
  }
}
