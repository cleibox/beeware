/**
 * Bee
 * Desc: Bee properties
 * @author Cynthia L & Phoebe Y ICS4U
 * @version 1.2 Jun 2022
 */

package mob;

import main.GamePanel;
import sprites.Sprites;
import java.io.File;
import javax.imageio.ImageIO;

// import javafx.scene.shape.Rectangle;
// import java.awt.Graphics2D;
import java.awt.Rectangle;

import java.io.IOException;
import java.util.Random;

public class Bee extends Sprites {
    public Bee(GamePanel game){
        super(game);

        direction = "down";
        speed = 5;

        solid = new Rectangle();
        solid.x = 1;
        solid.y = 1;
        solid.width = 12;
        solid.height = 9;
        solidAreaDefaultX = solid.x;
        solidAreaDefaultY = solid.y;

        getImage();
    }
    
    public void getImage(){
        try{
            left = ImageIO.read(new File ("mob/bees_left.png"));
            left1 = ImageIO.read(new File ("mob/bees_left.png"));
            right = ImageIO.read(new File ("mob/bees_right.png"));
            right1 = ImageIO.read(new File ("mob/bees_right.png"));
            up = ImageIO.read(new File ("mob/bees_up.png"));
            up1 = ImageIO.read(new File ("mob/bees_up.png"));
            up2 = ImageIO.read(new File ("mob/bees_up.png")); 
            down = ImageIO.read(new File ("mob/bees_down.png"));
            down1 = ImageIO.read(new File ("mob/bees_down.png"));
            down2 = ImageIO.read(new File ("mob/bees_down.png"));
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public void setAction(){
        if (onPath == true){
            int goalCol = 1;
            int goalRow = 1;

            searchPath(goalCol, goalRow);
            
        } else {
            actionLockCounter++;
    
            if (actionLockCounter == 120){
                Random random = new Random();
                int i = random.nextInt(100)+1; // number from 1 to 100
        
                if (i <= 25){
                    direction = "up";
                }
                if (i > 25 && i  <= 50){
                    direction = "down";
                }
                if (i > 50 && i <= 75){
                    direction = "left";
                }
                if (i > 75 && i <= 100) {
                    direction = "right";
                }
    
                actionLockCounter = 0; // don't change turning direction for the next 120 frames (2 sec)
            }
        
        }

    }
}
