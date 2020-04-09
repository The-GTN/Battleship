package battleship.util;
import battleship.board.Sea;
import battleship.util.*;
import battleship.io.Input;
import java.io.IOException;

/**
 * The Game of battleship
 *
 * @author <a href="mailto:antoine.nollet.etu@univ-lille.fr">Nollet Antoine</a>
 * @version 1.0
 */
public class Game{

  /* the board of this Game */
  private Sea sea;

  /** Create a Game from the Sea sea
  * @param sea the Sea where we're going to play
  */
  public Game(Sea sea) {
    this.sea = sea;
  }

  /**
  * Play to this Game
  */
  public void play() {
    int round = 0;
    System.out.println("Welcome to a new Game of Battleship.");
    System.out.println("");
    Position pos;
    Answer answer;
    String str;
    boolean quit = false;
    while(this.sea.getLife()!=0 && !quit) {
      sea.display(false);
      pos = this.askPosition();
      while(this.sea.notValidPosition(pos) && !quit) {
        System.out.println("It's not a valid Position !");
        System.out.println("Do you want to quit ? Answer 'y' if you want to quit.");
        str = Input.readString();
        if (str.equals("y")) quit = true;
        else {
          System.out.println("Okay. So please, give a valid Position...");
          pos = this.askPosition();
        }
      }
      if(!quit) {
        round++;
        answer = this.sea.shoot(pos);
        System.out.println("");
        if (answer == Answer.HIT) {
          System.out.println("HIT !");
        }
        else if (answer == Answer.MISSED) {
          System.out.println("MISSED...");
        }
        else if (answer == Answer.SUNK) {
          System.out.println("SUNK !!");
        }
        System.out.println("");
      }
    }
    if(!quit) {
      System.out.println("You win in "+round+" rounds.");
      System.out.println("See you later !");
    }
    else {
      System.out.println("See you later !");
    }
  }

  /** Return a input Position
  * @return the position we input
  */
  public Position askPosition() {
    Position pos;
    int x;
    int y;
    try {
      System.out.println("");
      System.out.println("give a coordinate x :");
      x = Input.readInt();
    }
    catch (java.io.IOException e) {
      x = 1000000;
    }
    try {
      System.out.println("");
      System.out.println("give a coordinate y :");
      y = Input.readInt();
    }
    catch (java.io.IOException e) {
      y = 1000000;
    }
    pos = new Position(x,y);
    return pos;
  }

}
