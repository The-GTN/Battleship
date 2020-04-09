package battleship.board;
import battleship.util.*;
import battleship.ship.Ship;
import battleship.board.Cell;

/**
 * The Sea, the board of the Battleship game
 *
 * @author <a href="mailto:antoine.nollet.etu@univ-lille.fr">Nollet Antoine</a>
 * @version 1.0
 */
 public class Sea {

   /*the board of battleship*/
   private Cell[][] grid;
   /*the sum of the HP of the ships in this Sea*/
   private int life;
   /*the number of ships*/
   private int ships;

   /**
   * Create a Sea
   * @param height the height of this Sea
   * @param width the width of this Sea
   */
   public Sea(int height, int width) {
     this.life = 0;
     this.ships = 0;
     this.grid = new Cell[height][width];
     for (int y = 0;y!=height;y++) {
       for (int x = 0;x!=width;x++) {
         this.grid[y][x] = new Cell();
       }
     }
   }

   /** Return the sum of the HP of the ships in this Sea
   * @return the sum of the HP of the ships in this Sea
   */
   public int getLife() {
     return this.life;
   }

   /** Return the number of ships in this Sea
   * @return the number of ships in this Sea
   */
   public int getShips() {
     return this.ships;
   }

   /**
   * Check if the Position p is not valid
   * @param p the position
   * @return if the position is not valid
   */
   public boolean notValidPosition(Position p) {
     return p.getX() < 0 || p.getY() < 0 || p.getX() >= this.grid[0].length || p.getY() >= this.grid.length;
   }

   /**
   * Get a cell by a Position
   * @param p the position of the cell
   * @return the cell of position p in this Sea
   */
   public Cell getCell(Position p) {
     int x = p.getX();
     int y = p.getY();
     return this.grid[y][x];
   }

   /**
   * Shoot in the sea in a Position p
   * @param p the position where we shoot
   * @return an Answer : MISSED, HIT or SUNK
   * @exception ArrayIndexOutOfBoundsException when Position not in the sea
   */
   public Answer shoot(Position p) throws ArrayIndexOutOfBoundsException{
     if(this.notValidPosition(p)) {
       throw new ArrayIndexOutOfBoundsException("Not valid Position !");
     }
     else {
       Answer answer = this.getCell(p).shoot();
       if (answer != Answer.MISSED) this.life--;
       if (answer == Answer.SUNK) this.ships--;
       return answer;
     }
   }

   /** Displays the board line by line and cell by cell,
   *  the display changes for the defender or the opponent, defined
   *  by the <code>defender</code> argument
   * @param defender <code>true</code> if display id for the defender
   *      <code>false</code> if for opponent
   */
   public void display(boolean defender) {
     if(defender) {
       System.out.println("Your Sea :");
       System.out.println("");
     }
     else {
       System.out.println("Your Opponent's Sea :");
       System.out.println("");
     }
     System.out.println("   0 1 2 3 4 5 6 7 8 9 ");
     System.out.println("  +-+-+-+-+-+-+-+-+-+-+");
     for (int y = 0;y!=this.grid.length;y++) {
       String line = y+" |";
       for (int x = 0;x!= this.grid[0].length;x++) {
         Position pos = new Position(x,y);
         Cell cell = this.getCell(pos);
         line = line + cell.getCharacter(defender) + "|";
       }
        System.out.println(line);
        System.out.println("  +-+-+-+-+-+-+-+-+-+-+");
     }
     System.out.println("");
     System.out.println("Your opponent has "+this.life+"HP with "+this.ships+" ships.");
     System.out.println("");
   }

   /** return a table of length len
   * @param len the length of the ship
   * @param position the first position
   * @param vertical true if we add vertically, false if we add horizontally
   * @return the table of positions
   */
   public Position[] thePositions(int len, Position position, boolean vertical) {
     Position[] res = new Position[len];
     int Y = position.getY();
     int X = position.getX();
     if (vertical) {
       for (int y=0; y!=len; y++) {
         res[y] = new Position(X,Y+y);
       }
     }
     else {
       for (int x=0; x!=len; x++) {
         res[x] = new Position(X+x,Y);
       }
     }
     return res;
   }

   /** add the ship b vertically down or horizontally to the right from position p.
   * The number of cells is determined by the ship length.
   * @param shipToPlace the ship to add
   * @param position the position of the first (top) cell occupied by the ship
   * @param vertical true if we add vertically, false if we add horizontally
   * @throws IllegalStateException if the ship b can not be placed on the sea
   * (outside of the board or some cell is not empty)
   */
   public void addShip(Ship shipToPlace, Position position, boolean vertical) throws IllegalStateException {
     int len = shipToPlace.getHP();
     Position[] positions = thePositions(len,position,vertical);
     for (int i=0; i!=len;i++) {
       Position pos = positions[i];
       if (this.notValidPosition(pos) || !(this.getCell(pos).isEmpty())) {
         throw new IllegalStateException("You can't add your ship...");
       }
     }
     if(this.thereShipsAround(positions)) throw new IllegalStateException("You can't add your ship...");
     for (int i=0; i!=len;i++) {
       Position pos = positions[i];
       this.getCell(pos).setShip(shipToPlace);
       this.life++;
     }
     this.ships++;
   }

   /** return if there ships or not around the many Position in positions in this Sea
   * @param positions the many Position that we look around
   * @return if there ships or not around the many Position in positions
   */
   public boolean thereShipsAround(Position[] positions) {
     Cell[] around;
     for (int i = 0; i!=positions.length;i++) {
       around = this.cellAround(positions[i]);
       for(int j = 0; j!=around.length;j++) {
         if (!around[j].isEmpty()) return true;
       }
     }
     return false;
   }

   /** return the cells around the Position pos in this Sea
   * @param pos the Position that we look the cell around
   * @return the cells around
   */
   public Cell[] cellAround(Position pos) {
     Cell[] res;
     int len = 0;
     int x = pos.getX();
     int y = pos.getY();
     Position tmp;
     for(int l = -1; l!=2;l++){
       for(int c = -1;c!=2;c++) {
         tmp = new Position(x+l,y+c);
         if ( !this.notValidPosition(tmp) && x!=x+l && y != y+c) len++;
       }
     }
     res = new Cell[len];

     int i = 0;
     for(int l = -1; l!=2;l++){
       for(int c = -1;c!=2;c++) {
         tmp = new Position(x+l,y+c);
         if ( !this.notValidPosition(tmp) && l != 0 && c != 0) {
           res[i] = this.getCell(tmp);
           i++;
         }
       }
     }
     return res;
   }

   /** add the ship b vertically down from position p.
   * @see addShip(String, Position, boolean)
   */
   public void addShipVertically(Ship shipToPlace, Position position) throws IllegalStateException {
     addShip(shipToPlace,position,true);
   }

   /** add the ship b horizontally to right from position p.
   * @see addShip(String, Position, boolean)
   */
   public void addShipHorizontally(Ship shipToPlace, Position position) throws IllegalStateException {
     addShip(shipToPlace,position,false);
   }

   /** add randomly a ship in this Sea
   * @param shipToPlace th ship to place in this Sea
   */
   public void AddShipRandomly(Ship shipToPlace) {
     boolean fini = false;
     int max_x = this.grid[0].length;
     int max_y = this.grid.length;
     Position pos;
     int x;
     int y;
     int methode;
     while(!fini) {
       x = (int)(Math.random() * max_x);
       y = (int)(Math.random() * max_y);
       methode = (int)(Math.random() * 2);
       pos = new Position(x,y);
       if (methode == 0) {
         try {
           this.addShipVertically(shipToPlace, pos);
           fini = true;
         }
         catch (java.lang.IllegalStateException e) {
           fini = false;
         }
       }
       else{
         try {
           this.addShipHorizontally(shipToPlace, pos);
           fini = true;
         }
         catch (java.lang.IllegalStateException e) {
           fini = false;
         }
       }
     }
   }


 }
