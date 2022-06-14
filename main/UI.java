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

    public int commandNum = 0; // selection number on the menu
    public int rulesScreen = 0; // 0 means it's on the title screen; other on the rules screen
    
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
        if (rulesScreen == 0){
            // Title Background 
            g2.setColor(new Color(193, 225, 193));
            g2.fillRect(0, 0, game.screenWidth, game.screenHeight);
    
            // Title Name text
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,52F));
            String text = "BEEware of the Garden";
            
            int x = getXforCenteredText(text);
            int y = game.tileSize*3;
    
            g2.setColor(Color.white);
            g2.drawString(text, x, y);
    
            // player mascot image slay
            x = game.screenWidth/2 - game.tileSize;
            y += game.tileSize;
            g2.drawImage(game.user.playerLeft1, x, y, game.tileSize*2, game.tileSize*2, null);
    
            // Menu
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
            
            text = "PLAY";
            x = getXforCenteredText(text);
            y += game.tileSize*4;
            g2.drawString(text, x, y);
            if (commandNum == 0){ // selection arrow 
                g2.drawString(">", x-game.tileSize, y);
            }
    
            text = "RULES";
            x = getXforCenteredText(text);
            y += game.tileSize*1.5;
            g2.drawString(text, x, y);
            if (commandNum == 1){ // selection arrow 
                g2.drawString(">", x-game.tileSize, y);
            }
    
            text = "QUIT";
            x = getXforCenteredText(text);
            y += game.tileSize*1.5;
            g2.drawString(text, x, y);
            if (commandNum == 2){ // selection arrow 
                g2.drawString(">", x-game.tileSize, y);
            }
        }
        else if (rulesScreen == 1){ // rules screen
            // Rules Background 
            g2.setColor(new Color(0, 0, 0));
            g2.fillRect(0, 0, game.screenWidth, game.screenHeight);
    
            // Title Name text
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,52F));
            String text = "Rules";
            
            int x = getXforCenteredText(text);
            int y = game.tileSize*3;
    
            g2.setColor(Color.white);
            g2.drawString(text, x, y);
    
            // Menu
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
            
            text = "bee bad flower good";
            x = getXforCenteredText(text);
            y += game.tileSize*4;
            g2.drawString(text, x, y);

            text = "BACK";
            x = getXforCenteredText(text);
            y += game.tileSize*3;
            g2.drawString(text, x, y);
            g2.drawString(">", x-game.tileSize, y);
        }
    }

    public void drawRulesScreen(){
        System.out.println("HIHFDSFDSFDS");
        // Title Background 
         g2.setColor(new Color(193, 225, 193));
         g2.fillRect(0, 0, game.screenWidth, game.screenHeight);
 
         // rules text
         g2.setFont(g2.getFont().deriveFont(Font.BOLD,52F));
         String text = "Rules";
         
         int x = getXforCenteredText(text);
         int y = game.tileSize*3;
 
         g2.setColor(Color.white);
         g2.drawString(text, x, y);
 
         g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
         
         text = "PLAY";
         x = getXforCenteredText(text);
         y += game.tileSize*2;
         g2.drawString(text, x, y);

    }

    public int getXforCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int centerX = game.screenWidth/2 - length/2;
        return centerX;
    }
}
