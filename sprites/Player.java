package sprites;

import main.GamePanel;
import main.MyKeyListener;
import java.awt.Graphics2D;
import java.awt.Color;

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
