package battleship.ship;
import battleship.ship.ModelShip;


/**
 * A ship of the Battleship game
 *
 * @author <a href="mailto:antoine.nollet.etu@univ-lille.fr">Nollet Antoine</a>
 * @version 1.0
 */

 public class Ship {
   /*the HP / length of the ship*/
   private int HP;

   /**
   * Create a ship
   *
   * @param model The model of the ship we want to create
   */
   public Ship(ModelShip model) {
     if (model == ModelShip.PORTE_AVION){
       this.HP = 5;
     }
     else if(model == ModelShip.CROISEUR){
       this.HP = 4;
     }
     else if(model == ModelShip.CONTRE_TORPILLEURS){
       this.HP = 3;
     }
     else {
       this.HP = 2;
     }
   }

   /**
   * Return the number of HP of this ships
   * @return the number of HP of this ships
   */
   public int getHP() {
     return this.HP;
   }

   /**
   * Return if the ship is dead
   * @return if the ship is dead
   */
   public boolean isSunk() {
     return (this.HP == 0);
   }

   /**
   * Hit this ship and make it loose 1HP, except if this ship has already 0HP
   */
   public void hit() {
     if (!this.isSunk()) {
       this.HP--;
     }
   }

 }
