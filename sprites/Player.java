 /**
  * Player
  * Desc: Sets up and runs player functions.
  * @author Cynthia L & Phoebe Y ICS4U
  * @version 1.2 Jun 2022
  */

package sprites;

import main.GamePanel;
import main.MyKeyListener;
import java.awt.Graphics2D;
//import java.awt.Color;

// the following imports are needed for pictures
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends Sprites {
    
    GamePanel game;
    MyKeyListener key;
    
    public final int screenX;
    public final int screenY;
    
    // Objects
    public int numTulipCollected = 0;

    //player constructor
    public Player(GamePanel game, MyKeyListener key){
        this.game = game;
        this.key = key;

        //setting player at center of the game screen
        screenX= (game.screenWidth/2)-18;
        screenY= (game.screenHeight/2)-25;

        //collision detection rectangle
        //player properties
        int playerX = 8;
        int playerY = 18;
        int playerH = 32;
        int playerW = 24;
        solid = new Rectangle(playerX, playerY, playerH, playerW);
        solid.x = 8;
        solid.y = 16;
        solidAreaDefaultX = solid.x;
        solidAreaDefaultY = solid.y;

        setDefault();
        getPlayerImage();
    }

    //player starting position on map (center)
    public void setDefault(){
        mapX= game.tileSize * 25;
        mapY = game.tileSize * 25;
        speed = 20;
        direction = "down";
    }

    public void getPlayerImage(){ // load in the character sprite sheet
        try{
            //beeLeft = ImageIO.read(new File ("images/bee_left.png"));
            //beeRight = ImageIO.read(new File ("images/bee_right.png"));
            playerUp = ImageIO.read(new File ("images/man_up.png"));
            playerUp1 = ImageIO.read(new File ("images/man_up_step_1.png"));
            playerUp2 = ImageIO.read(new File ("images/man_up_step_2.png"));
            playerDown = ImageIO.read(new File ("images/man_down.png"));
            playerDown1 = ImageIO.read(new File ("images/man_down_step_1.png"));
            playerDown2 = ImageIO.read(new File ("images/man_down_step_2.png"));
            playerLeft = ImageIO.read(new File ("images/man_left.png"));
            playerLeft1 = ImageIO.read(new File ("images/man_left_step.png"));
            playerRight = ImageIO.read(new File ("images/man_right.png"));
            playerRight1 = ImageIO.read(new File ("images/man_right_step.png"));

        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public void update(){ // gets called 60 times per second
        playerHealth(); // check if the game is over
        
        if (key.upPressed == true || key.downPressed == true || 
            key.leftPressed == true || key.rightPressed == true){
                
            //checking for direction    
            if (key.upPressed == true){
                direction = "up";
            }
            else if (key.downPressed == true){
                direction = "down";
            }
            else if (key.leftPressed == true){
                direction = "left";
            }
            else if (key.rightPressed == true){
                direction = "right";
            }

            //checking for tile collision
            collided = false;
            game.detector.tileDetection(this);

            // check object collisions
            int objIndex = game.detector.checkObject(this, true); // this is player so it's true
            pickUpObject(objIndex);

            //ALLOWS movement only if collision is NOT detected
            if (collided == false ){
                if (direction.equals ("up")){
                    mapY-= speed;
                } else if (direction.equals("down")){
                    mapY+= speed;
                } else if (direction.equals("left")){
                    mapX-= speed;
                }else if (direction.equals("right")){
                    mapX+= speed;
                }
            }
    
            spriteCounter++; //player image changes every 10 frames
            if (spriteCounter > 10){
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if (spriteNum == 2 || spriteNum == 3){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        else { // idling up/down
            if (direction.equals("up") || direction.equals("down")){
                spriteNum = 3;
            }
        }

    }

    public void pickUpObject(int i){
        if (i != 999) { // if not 999, then we have touched an object
            String objectName = game.obj[i].name;
            if (objectName.equals("Tulip")){
                numTulipCollected++;
                System.out.println("Total tulips: " + numTulipCollected);
                game.obj[i] = null; // delete the object we touched
                game.aSetter.spawnTulip();
            }
            else if (objectName.equals("Nightshade")){
                game.obj[i] = null; // delete the object we touched
                game.aSetter.spawnNightshade();
            }
        }
    }

    public void playerHealth(){ // for now, if collect 5 tulip, game end
        if (numTulipCollected == 1){
            game.gameScreen = game.endScreen;
        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;

        if (direction.equals("up")){
            if (spriteNum == 1){
                image = playerUp2;  
            }
            if (spriteNum ==2){
                image = playerUp1;
            }
            if (spriteNum == 3){
                image = playerUp;
            }
        } else if (direction.equals("down")){
            if (spriteNum == 1){
                image = playerDown2;  
            }
            if (spriteNum ==2){
                image = playerDown1;
            }
            if (spriteNum == 3){
                image = playerDown;
            }
        } else if (direction.equals("left")){
            if (spriteNum == 1 || spriteNum == 3){
                image = playerLeft;
            }
            if (spriteNum == 2){
                image = playerLeft1;
            }
        } else if (direction.equals("right")){
            if (spriteNum == 1 || spriteNum == 3){
                image = playerRight;
            }
            if (spriteNum ==2){
                image = playerRight1;
            }
        }
        //drawing image on screen
        g2.drawImage(image, screenX, screenY, game.tileSize, game.tileSize, null);
        
        game.timeLapsed += (double)1/60;
        System.out.println("TIME" + game.timeLapsed); // measure time lapsed
    }
}
