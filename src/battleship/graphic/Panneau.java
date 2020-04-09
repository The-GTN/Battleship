package battleship.graphic;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import battleship.board.*;
import battleship.util.*;

public class Panneau extends JPanel{

  private Sea sea;

  public Panneau(Sea sea) {
    this.sea = sea;
  }

  public void paintComponent(Graphics g){
    try {
      Image notShoot = ImageIO.read(new File("../media/pictures/notShoot.png"));
      Image hit = ImageIO.read(new File("../media/pictures/hit.png"));
      Image water = ImageIO.read(new File("../media/pictures/water.png"));
      Position pos;
      Cell cell;
      for(int y = 0;y!=10;y++) {
        for(int x = 0;x != 10;x++) {
          pos = new Position(x,y);
          cell = this.sea.getCell(pos);
          if(!cell.hasBeenShot()) g.drawImage(notShoot, x*30, y*30, this);
          else if(cell.isEmpty()) g.drawImage(water, x*30, y*30, this);
          else {
            g.drawImage(hit, x*30, y*30, this);
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
