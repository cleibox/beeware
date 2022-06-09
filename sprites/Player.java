package sprites;

import main.GamePanel;
import main.MyKeyListener;
import java.awt.Graphics2D;
import java.awt.Color;

// the following imports are needed for pictures
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Player extends Sprites {
    
    GamePanel game;
    MyKeyListener key;

    //player constructor
    public Player(GamePanel game, MyKeyListener key){
        this.game = game;
        this.key = key;

        setDefault();
    }

    public void setDefault(){
        x = 100;
        y = 100;
        speed = 4;
    }
    public void getPlayerImage(){
        try{
            beeLeft = ImageIO.read(new File ("images\\bee_left.png"));
            beeRight = ImageIO.read(new File ("images\\bee_right.png"));
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

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        if (key.upPressed == true){
            y -= speed;
        }
        else if (key.downPressed == true){
            y += speed;
        }
        else if (key.leftPressed == true){
            x -= speed;
        }
        else if (key.rightPressed == true){
            x += speed;
    }
}
    public void draw(Graphics2D g2){
        g2.setColor(Color.white);
        g2.fillRect(x, y, game.tileSize, game.tileSize);
    }
}
