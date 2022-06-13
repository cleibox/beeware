 /**
  * UI
  * Desc: Sets up the different game screens (title, game)
  * @author Cynthia L & Phoebe Y ICS4U
  * @version 1.2 Jun 2022
  */

package main;

// import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Color;

public class UI {
    GamePanel game;
    Graphics2D g2;
    
    public UI(GamePanel game){
        this.game = game;        
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;


        // Title Screen
        if (game.gameScreen == game.titleScreen){
            drawTitleScreen();
        }
    }

    public void drawTitleScreen(){
        // Title Background 
        g2.setColor(new Color(193, 225, 193));
        g2.fillRect(0, 0, game.screenWidth, game.screenHeight);

        // Title Name text
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
        String titleText = "BEEware of the Garden";
        
        int x = getXforCenteredText(titleText);
        int y = game.tileSize*3;

        g2.setColor(Color.white);
        g2.drawString(titleText, x, y);

        // player mascot image slay
        x = game.screenWidth/2 - game.tileSize;
        y += game.tileSize*2;
        g2.drawImage(game.user.playerLeft1, x, y, game.tileSize*2, game.tileSize*2, null);

    }

    public int getXforCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int centerX = game.screenWidth/2 - length/2;
        return centerX;
    }
}
