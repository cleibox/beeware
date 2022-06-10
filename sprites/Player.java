package sprites;

import main.GamePanel;
import main.MyKeyListener;
import java.awt.Graphics2D;
import java.awt.Color;

// the following imports are needed for pictures
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;


public class Player extends Sprites {
    
    GamePanel game;
    MyKeyListener key;

    //player constructor
    public Player(GamePanel game, MyKeyListener key){
        this.game = game;
        this.key = key;

        setDefault();
        getPlayerImage();
    }

    public void setDefault(){
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage(){
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

    public void update(){ //gets called 60 times per second
        if (key.upPressed == true){
            direction = "up";
            y -= speed;
        }
        else if (key.downPressed == true){
            direction = "down";
            y += speed;
        }
        else if (key.leftPressed == true){
            direction = "left";
            x -= speed;
        }
        else if (key.rightPressed == true){
            direction = "right";
            x += speed;
    }
    spriteCounter++; //player image changes every 10 frames
    if (spriteCounter > 10){
        if(spriteNum == 1){
            spriteNum = 2;
        }
        else if (spriteNum == 2){
            spriteNum = 1;
        }
        spriteCounter = 0;
    }
}

    public void draw(Graphics2D g2){
        BufferedImage image = null;
        if (direction.equals("up")){
            if (spriteNum == 1){
                image = playerUp;  
            }
            if (spriteNum ==2){
                image = playerUp1;
            }
        }else if (direction.equals("down")){
            if (spriteNum == 1){
                image = playerDown; 
            }
            if (spriteNum ==2){
                image = playerDown1;
            }
        }else if (direction.equals("left")){
            if (spriteNum == 1){
                image = playerLeft;
            }
            if (spriteNum ==2){
                image = playerLeft1;
            }
        }else if (direction.equals("right")){
            if (spriteNum == 1){
                image = playerRight;
            }
            if (spriteNum ==2){
                image = playerRight1;
            }
        }
        //drawing image on screen
        g2.drawImage(image, x, y, game.tileSize, game.tileSize, null);
    }
}
