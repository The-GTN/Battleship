import org.junit.*;
import static org.junit.Assert.*;

import battleship.util.Position;

public class TestPosition {

  @Test
  public void TestX() {
    Position pos = new Position(2,3);
    assertEquals(2,pos.getX());
  }

  @Test
  public void TestY() {
    Position pos = new Position(2,3);
    assertEquals(3,pos.getY());
  }

  @Test
  public void testEquals() {
    Position pos = new Position(2,3);
    Position pos2 = new Position(2,3);
    assertEquals(pos2,pos);
  }

  @Test
  public void testString() {
    Position pos = new Position(2,3);
    assertEquals("(2,3)",pos.toString());
  }

  @Test
  public void noNegativePosition() {
    Position pos = new Position(-2,-3);
    assertEquals(0,pos.getX());
    assertEquals(0,pos.getX());
  }

  // ---Pour permettre l'exécution des test----------------------
  public static junit.framework.Test suite() {
      return new junit.framework.JUnit4TestAdapter(TestPosition.class);

  }

}
