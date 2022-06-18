/**
 * Bee
 * Desc: Bee properties
 * @author Cynthia L & Phoebe Y ICS4U
 * @version 1.2 Jun 2022
 */

package mob;

import main.GamePanel;
import sprites.Sprites;
import java.awt.Rectangle;

import java.util.Random;

public class Bee extends Sprites {
    public Bee(GamePanel game){
        super(game);

        direction = "down";
        speed = 8;

        solidArea = new Rectangle();
        solidArea.x = 3;
        solidArea.y = 4;
        solidArea.width = 40;
        solidArea.height = 42;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }
    
    public void getImage(){
        left = setup("mob/bees_left");
        left1 = setup("mob/bees_left");
        right = setup("mob/bees_right");
        right1 = setup("mob/bees_right");
        up = setup("mob/bees_up");
        up1 = setup("mob/bees_up");
        up2 = setup("mob/bees_up");
        down = setup("mob/bees_down");
        down1 = setup("mob/bees_down");
        down2 = setup("mob/bees_down");
    }

    public void setAction(){
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
