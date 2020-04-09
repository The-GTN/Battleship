import org.junit.*;
import static org.junit.Assert.*;

import battleship.ship.*;


public class TestShip {

  @Test
  public void goodNumberOfHP() {
    Ship ship = new Ship(ModelShip.CROISEUR);
    assertEquals(4,ship.getHP());
  }


  @Test
  public void looseHP() {
    Ship ship = new Ship(ModelShip.PORTE_AVION);
    ship.hit();
    assertEquals(4,ship.getHP());
  }

  @Test
  public void sunk(){
    Ship ship = new Ship(ModelShip.TORPILLEUR);
    ship.hit();
    assertEquals(1,ship.getHP());
    ship.hit();
    assertTrue(ship.isSunk());
  }

  @Test
  public void noNegativeHP(){
    Ship ship = new Ship(ModelShip.TORPILLEUR);
    ship.hit();
    ship.hit();
    assertTrue(ship.isSunk());
    assertEquals(0,ship.getHP());
    ship.hit();
    assertEquals(0,ship.getHP());
  }

  // ---Pour permettre l'exécution des test----------------------
  public static junit.framework.Test suite() {
      return new junit.framework.JUnit4TestAdapter(TestShip.class);
  }
}
