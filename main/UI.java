 /**
  * UI
  * Desc: Sets up the different game screens (title, game)
  * @author Cynthia L & Phoebe Y ICS4U
  * @version 1.2 Jun 2022
  */

package main;

// IMPORTS (graphics)
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Color;

public class UI {
    GamePanel game;
    Graphics2D g2;
    Font arial40;

    public int commandNum = 0; // selection number on the menu
    public int rulesScreen = 0; // 0 means it's on the title screen; other on the rules screen
    
    public UI(GamePanel game){
        this.game = game;        

        arial40 = new Font("Calibri", Font.PLAIN, 40);
    }

    // Draws the corresponding screen
    public void draw(Graphics2D g2){
        this.g2 = g2;

        // ----------------------------------------------------------|
        // DRAW TITLE SCREEN ----------------------------------------|
        // ----------------------------------------------------------|
        if (game.gameScreen == game.titleScreen){
            drawTitleScreen();
        }
        // ----------------------------------------------------------|
        // DRAW GAME SCREEN -----------------------------------------|
        // ----------------------------------------------------------|
        else if (game.gameScreen == game.playScreen){
            drawGameScreen();
        }
        // ----------------------------------------------------------|
        // DRAW END SCREEN ------------------------------------------|
        // ----------------------------------------------------------|
        else if (game.gameScreen == game.endScreen){
            drawEndScreen();
        }

    }

    public void drawTitleScreen(){
        // ----------------------------------------------------------|
        // TITLE DRAW COMPONENTS ------------------------------------|
        // ----------------------------------------------------------|
        if (rulesScreen == 0){
            // ----------------------------------------------------------|
            // TITLE BACKGROUND -----------------------------------------|
            // ----------------------------------------------------------|
            g2.setColor(new Color(193, 225, 193)); // Background Color
            g2.fillRect(0, 0, game.screenWidth, game.screenHeight); // Fill Background
    
            // ----------------------------------------------------------|
            // TITLE TEXT -----------------------------------------------|
            // ----------------------------------------------------------|
            g2.setColor(Color.white); // Text Color
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 52F)); // Text Font 
            String text = "BEEware of the Garden"; // Text 
            int x = getXforCenteredText(text); // Text xCoordinates
            int y = game.tileSize*3; // Text yCoordinates
            g2.drawString(text, x, y); // Display Text
    
            // ----------------------------------------------------------|
            // GAME IMAGE -----------------------------------------------|
            // ----------------------------------------------------------|
            x = game.screenWidth/2 - game.tileSize; // Image xCoordinates
            y += game.tileSize; // Image yCoordinates
            g2.drawImage(game.user.left1, x, y, game.tileSize*2, game.tileSize*2, null); // Display Image
    
            // ----------------------------------------------------------|
            // MENU SELECTION TEXT --------------------------------------|
            // ----------------------------------------------------------|
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F)); // Change Text Font
            
            // "PLAY" Selection
            text = "PLAY"; 
            x = getXforCenteredText(text);
            y += game.tileSize*4;
            g2.drawString(text, x, y); 
            if (commandNum == 0){ // selection arrow 
                g2.drawString(">", x-game.tileSize, y);
            }
    
            // "RULES" Selection
            text = "RULES";
            x = getXforCenteredText(text);
            y += game.tileSize*1.5;
            g2.drawString(text, x, y);
            if (commandNum == 1){ // selection arrow 
                g2.drawString(">", x-game.tileSize, y);
            }
    
            // "QUIT" Selection
            text = "QUIT";
            x = getXforCenteredText(text);
            y += game.tileSize*1.5;
            g2.drawString(text, x, y);
            if (commandNum == 2){ // selection arrow 
                g2.drawString(">", x-game.tileSize, y);
            }
        }
        // ----------------------------------------------------------|
        // RULES DRAW COMPONENTS ------------------------------------|
        // ----------------------------------------------------------|
        else if (rulesScreen == 1){ 
            // ----------------------------------------------------------|
            // RULES BACKGROUND -----------------------------------------|
            // ----------------------------------------------------------| 
            g2.setColor(new Color(0, 0, 0)); // Background Color
            g2.fillRect(0, 0, game.screenWidth, game.screenHeight); // Fill Background
    
            // ----------------------------------------------------------|
            // RULES TITLE TEXT -----------------------------------------|
            // ----------------------------------------------------------|
            g2.setColor(Color.white); // Text Color
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,52F)); // Text Font
            String text = "Rules";
            int x = getXforCenteredText(text); // Text xCoordinates
            int y = game.tileSize*2; // Text yCoordinates
            g2.drawString(text, x, y); // Display Text
    
            // ----------------------------------------------------------|
            // RULES BODY TEXT ------------------------------------------|
            // ----------------------------------------------------------|
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,20F)); // Change Text Font
            String textMainBody = "The goal of the game is to collect as many flowers as you can \nwhile you have enough health points to stay alive from the bees.";
            x = 85;
            y += game.tileSize*1; 
            for (String line: textMainBody.split("\n")){ //split text to next line when \n is typed
                g2.drawString(line, x, y);
                // System.out.println (x +" " + y);
                y += 40;
            }
            //String textBeeDetails

            // ----------------------------------------------------------|
            // RULES IMAGES ---------------------------------------------|
            // ----------------------------------------------------------|
            x = game.screenWidth/2 - game.tileSize; // Image xCoordinates
            y += game.tileSize; // Image yCoordinates
            g2.drawImage(game.user.left, x, y, game.tileSize*2, game.tileSize*2, null); // Display Image

            // ----------------------------------------------------------|
            // RULES SELECTION TEXT -------------------------------------|
            // ----------------------------------------------------------|
            text = "BACK";
            x = getXforCenteredText(text);
            y += game.tileSize*6;
            g2.drawString(text, x, y);
            g2.drawString(">", x-game.tileSize, y); // Arrow
        }
    }

    public void drawGameScreen(){
        // ----------------------------------------------------------|
        // IN GAME TEXT ---------------------------------------------|
        // ----------------------------------------------------------|
        g2.setFont(arial40);
        g2.setColor(Color.white);
        g2.drawString("Tulips: " + game.user.numTulipCollected, 25, game.tileSize);
        g2. drawString("Health: " + game.user.playerHealth, 25, game.tileSize * 2);
    }

    public void drawEndScreen(){
        // ----------------------------------------------------------|
        // END BACKGROUND -------------------------------------------|
        // ----------------------------------------------------------|
        g2.setColor(new Color(193, 225, 193)); 
        g2.fillRect(0, 0, game.screenWidth, game.screenHeight);

        // ----------------------------------------------------------|
        // END TITLE TEXT -------------------------------------------|
        // ----------------------------------------------------------|
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,52F));
        String text = "GAME OVER";        
        int x = getXforCenteredText(text);
        int y = game.tileSize*3;
        g2.drawString(text, x, y);
        
        // ----------------------------------------------------------|
        // END BODY TEXT --------------------------------------------|
        // ----------------------------------------------------------|
        text = "You collected " + game.user.numTulipCollected + " tulip(s)";
        x = getXforCenteredText(text);
        y += game.tileSize*3;
        g2.drawString(text, x, y);
       
        // ----------------------------------------------------------|
        // END SELECTION TEXT ---------------------------------------|
        // ----------------------------------------------------------|
        text = "QUIT";
        x = getXforCenteredText(text);
        y += game.tileSize*4;
        g2.drawString(text, x, y);
        g2.drawString(">", x-game.tileSize, y); 
    }

    public int getXforCenteredText(String text){ // Centre the text
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int centerX = game.screenWidth/2 - length/2;
        return centerX;
    }
}
