/**
 * Sprites
 * Desc: Sets up and runs sprite funtions
 * @author Cynthia L & Phoebe Y ICS4U
 * @version 1.2 Jun 2022
 */

package sprites;

// IMPORTS (classes)
import main.GamePanel;
import main.UtilityTool;

// IMPORTS (graphics)
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Rectangle;

// IMPORTS (file reading)
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;

public class Sprites {
  public GamePanel game;

  public int mapX, mapY;
  public int speed;

  public BufferedImage up, up1, up2, down, down1, down2, left, left1, right, right1;
  public BufferedImage tulip, nightShade;
  public BufferedImage beeLeft;

  public String direction = "down";
  public int spriteCounter = 0;
  public int spriteNum = 1;

  // collision detection
  public Rectangle solidArea;
  public boolean collided = false;
  public int actionLockCounter = 0;

  public String name;

  public int solidAreaDefaultX, solidAreaDefaultY;

  public Sprites(GamePanel game) {
    this.game = game;
  }

  public void setAction() {
  } // how the sprite behaves (moving)

  public void checkCollision() {
    collided = false;

    game.detector.tileDetection(this);
    game.detector.checkPlayerBeeCollision(this);
    game.detector.checkSpriteObjectCollision(this, game.obj); // check user colliding with objects (flowers)
  }

  public void update() {
    setAction();

    checkCollision();

    // ALLOWS movement only if collision is NOT detected
    if (collided == false) {
      if (direction.equals("up")) {
        mapY -= speed;
      } else if (direction.equals("down")) {
        mapY += speed;
      } else if (direction.equals("left")) {
        mapX -= speed;
      } else if (direction.equals("right")) {
        mapX += speed;
      }
    }

    spriteCounter++; // player image changes every 10 frames
    if (spriteCounter > 10) {
      if (spriteNum == 1) {
        spriteNum = 2;
      } else if (spriteNum == 2 || spriteNum == 3) {
        spriteNum = 1;
      }
      spriteCounter = 0;
    }

  }

  public void draw(Graphics2D g2) {
    BufferedImage image = null;

    int screenX = mapX - game.user.mapX + game.user.screenX;
    int screenY = mapY - game.user.mapY + game.user.screenY;

    // only draw tiles within screen (nost entire map) --> more efficient, less
    // glitchy
    if (((mapX + game.tileSize) > (game.user.mapX - game.user.screenX)) &&
        ((mapX - game.tileSize) < (game.user.mapX + game.user.screenX)) &&
        ((mapY + game.tileSize) > (game.user.mapY - game.user.screenY)) &&
        ((mapY - game.tileSize) < (game.user.mapY + game.user.screenY))) {

      if (direction.equals("up")) {
        if (spriteNum == 1) {
          image = up2;
        }
        if (spriteNum == 2) {
          image = up1;
        }
        if (spriteNum == 3) {
          image = up;
        }
      } else if (direction.equals("down")) {
        if (spriteNum == 1) {
          image = down2;
        }
        if (spriteNum == 2) {
          image = down1;
        }
        if (spriteNum == 3) {
          image = down;
        }
      } else if (direction.equals("left")) {
        if (spriteNum == 1 || spriteNum == 3) {
          image = left;
        }
        if (spriteNum == 2) {
          image = left1;
        }
      } else if (direction.equals("right")) {
        if (spriteNum == 1 || spriteNum == 3) {
          image = right;
        }
        if (spriteNum == 2) {
          image = right1;
        }
      }
      g2.drawImage(image, screenX, screenY, game.tileSize, game.tileSize, null);
    }
  }

  public BufferedImage setup(String imagePath){
    UtilityTool uTool = new UtilityTool();
    BufferedImage image = null;

    try{
      //  image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
       image = ImageIO.read(new File (imagePath + ".png"));

       image = uTool.scaleImage(image, game.tileSize, game.tileSize);
    } catch(IOException e) {
        e.printStackTrace();
    }
    return image;
  }

}
