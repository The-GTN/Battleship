package battleship;
import battleship.util.*;
import battleship.board.*;
import battleship.ship.*;

/**
 * The Main class of the Battleship project of POO
 *
 * @author <a href="mailto:antoine.nollet.etu@univ-lille.fr">Nollet Antoine</a>
 * @version 1.0
 *
 */
public class Main{

  /** It's the main function.
   * @param args No argument to input
   */
 public static void main(String[] args) {
   Sea sea = new Sea(10,10);
   Ship ship1 = new Ship(ModelShip.TORPILLEUR);
   Ship ship2 = new Ship(ModelShip.CONTRE_TORPILLEURS);
   Ship ship3 = new Ship(ModelShip.CONTRE_TORPILLEURS);
   Ship ship4 = new Ship(ModelShip.CROISEUR);
   Ship ship5 = new Ship(ModelShip.PORTE_AVION);
   Ship[] ships = {ship1,ship2,ship3,ship4,ship5};
   for(int i = 0; i!=ships.length; i++) {
     sea.AddShipRandomly(ships[i]);
   }
   Game game = new Game(sea);
   game.play();
 }

}
