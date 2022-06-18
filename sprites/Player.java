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

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends Sprites {
    MyKeyListener key;
    
    // Player coordinates on the screen
    public final int screenX;
    public final int screenY;
    
    public int playerHealth = 30;
    
    // Objects
    public int numTulipCollected = 0;
    public int beeCount = 0;

    //player constructor
    public Player(GamePanel game, MyKeyListener key){
        super(game);
        this.key = key;

        //setting player at center of the game screen
        screenX= (game.screenWidth/2)-18;
        screenY= (game.screenHeight/2)-25;

        //collision detection rectangle
        //player properties
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 18;
        solidArea.width = 24;
        solidArea.height = 32;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y; 

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
        up = setup("images/man_up");
        up1 = setup("images/man_up_step_1");
        up2 = setup("images/man_up_step_2");
        down = setup("images/man_down");
        down1 = setup("images/man_down_step_1");
        down2 = setup("images/man_down_step_2");
        left = setup("images/man_left");
        left1 = setup("images/man_left_step");
        right = setup("images/man_right");
        right1 = setup("images/man_right_step");
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
            int objIndex = game.detector.checkSpriteObjectCollision(this, game.obj); // this is player so it's true
            pickUpObject(objIndex);

            // ALLOWS movement only if collision is NOT detected
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
                game.aSpawner.spawnTulip();
                game.user.playerHealth++;
                game.aSpawner.spawnBee(); // spawn bees per collected tulip
                game.aSpawner.spawnBee(); // spawn bees per collected tulip
            }
            else if (objectName.equals("Nightshade")){
                game.obj[i] = null; // delete the object we touched
                game.aSpawner.spawnNightshade();
                game.user.playerHealth -= 2;
                game.aSpawner.spawnBee(); // spawn bees per collected night shade
                game.aSpawner.spawnBee(); // spawn bees per collected tulip
            }
        }
    }

    public void playerHealth(){ // game end
        if (this.playerHealth <= 0){
            game.gameScreen = game.endScreen;
        }
        if (this.playerHealth < 5 && speed > 5){
            speed -= 5; // under an amount of health, the player becomes slower
        }
        else if (this.playerHealth > 5 && speed < 5){
            speed += 5;
        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;

        if (direction.equals("up")){
            if (spriteNum == 1){
                image = up2;  
            }
            if (spriteNum ==2){
                image = up1;
            }
            if (spriteNum == 3){
                image = up;
            }
        } else if (direction.equals("down")){
            if (spriteNum == 1){
                image = down2;  
            }
            if (spriteNum ==2){
                image = down1;
            }
            if (spriteNum == 3){
                image = down;
            }
        } else if (direction.equals("left")){
            if (spriteNum == 1 || spriteNum == 3){
                image = left;
            }
            if (spriteNum == 2){
                image = left1;
            }
        } else if (direction.equals("right")){
            if (spriteNum == 1 || spriteNum == 3){
                image = right;
            }
            if (spriteNum ==2){
                image = right1;
            }
        }
        //drawing image on screen
        g2.drawImage(image, screenX, screenY, null);
       
       }

   
}
