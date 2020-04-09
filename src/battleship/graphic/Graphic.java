package battleship.graphic;
import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import battleship.board.*;
import battleship.graphic.*;
import battleship.util.*;
import battleship.ship.*;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;

public class Graphic extends JFrame implements MouseListener {

  private Sea sea;
  private int HP;
  private int ships;
  private int rounds;

  public static void main(String[] args){
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
    Graphic fen = new Graphic(sea);
  }

  public Graphic(Sea sea){
    this.sea = sea;
    this.HP = 17;
    this.ships = 5;
    this.rounds = 0;
    this.setTitle("Battleship");

    Toolkit kit = Toolkit.getDefaultToolkit();
    Image img = kit.getImage("../media/pictures/icone.png");
    setIconImage(img);

    this.setSize(300, 330);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setContentPane(new Panneau(this.sea));
    this.setVisible(true);
    this.addMouseListener(this);
    this.setResizable(false);
  }

  public void Play(File sound) {
    try {
      Clip clip = AudioSystem.getClip();
      clip.open(AudioSystem.getAudioInputStream(sound));
      clip.start();
    }
    catch(Exception e) {}
  }

  public void mouseClicked(MouseEvent event) {
    this.rounds++;
    int X = event.getX();
    int Y = event.getY();
    Position pos = new Position((int)Math.floor((X-0)/30),(int)Math.floor((Y-30)/30));
    Answer ans = this.sea.shoot(pos);
    File sound;
    if(!this.sea.notValidPosition(pos)) {
      if (ans == Answer.HIT) {
        this.HP--;
        System.out.println("HIT !");
        sound = new File("../media/sounds/hit.wav");
      }
      else if (ans == Answer.SUNK) {
        this.HP--;
        this.ships--;
        System.out.println("SUNK !!");
        sound = new File("../media/sounds/sunk.wav");
      }
      else {
        sound = new File("../media/sounds/missed.wav");
      }
      if(this.ships != 0) {
        if(ans == Answer.SUNK || ans == Answer.HIT) {
          System.out.println("Your opponent has "+this.HP+"HP with "+this.ships+" ships.");
          System.out.println("");
        }
        this.setContentPane(new Panneau(this.sea));
        this.setVisible(true);
        Play(sound);
      }
      else{
        this.setVisible(false);
        this.dispose();
        System.out.println("");
        System.out.println("You win in "+this.rounds+" rounds.");
        System.out.println("~ See you later ~");
      }
    }
  }

  public void mouseEntered(MouseEvent event) {}

  public void mouseExited(MouseEvent event) {}

  public void mousePressed(MouseEvent event) {}

  public void mouseReleased(MouseEvent event) {}
}
