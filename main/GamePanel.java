 /**
  * GamePanel
  * Desc: Sets up and runs game functions
  * @author Cynthia L & Phoebe Y ICS4U
  * @version 1.2 Jun 2022
  */

package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import javax.swing.JPanel;
import sprites.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{ // implements Runnable to run thread
    // ----------------------------------------------------------|
    // SCREEN SETTINGS ------------------------------------------|
    // ----------------------------------------------------------|
    // Sprite/Tile sizes
    final int originalTileSize = 16; // 16 x 16 tile; default size of sprites, tiles 
    final int scale = 3; 
    public final int tileSize = originalTileSize * scale; // 48 x 48 tile

    // Screen resolution (16px by 12 px)
    // Screen Horizontal
    public final int maxScreenCol = 16;  
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels

    // Screen Vertical
    public final int maxScreenRow = 12; 
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // setting FPS (Frames per Second)
    int FPS = 60;

    // ----------------------------------------------------------|
    // MAP SETTINGS -------------------------------------------|
    // ----------------------------------------------------------|
    //50 x 50 map
    public final int maxMapCol = 50;
    public final int maxMapRow = 50;
    public final int mapWidth = tileSize * maxMapCol;
    public final int mapHeight = tileSize * maxMapRow;

    MyKeyListener key = new MyKeyListener(this); // tracks the inputted keys 

    Thread gameThread;// once started, keeps game running

    public CollisionDetection detector = new CollisionDetection(this);

    TileManager tile = new TileManager (this);

    public UI ui = new UI(this);

    // ----------------------------------------------------------|
    // DIFFERENT SCREENS ----------------------------------------|
    // ----------------------------------------------------------|
    public int gameScreen;
    public final int titleScreen = 0;
    public final int playScreen = 1;
    
    // ----------------------------------------------------------|
    // PLAYER SETTINGS ------------------------------------------|
    // ----------------------------------------------------------|
    public Player user = new Player (this, key); 

    public GamePanel () {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // set size of the JPanel
        this.setBackground(Color.black);
       
        this.setDoubleBuffered(true);// improve rendering
        
        this.addKeyListener(key); // recognize key input
        this.setFocusable (true); // GamePanel can focus on receiving key input
    }

    public void setupGame(){
        gameScreen = titleScreen; // begins with the title screen
    }
    
    public void startGameThread(){
        gameThread = new Thread(this); // passing gamepanel as parameter
        gameThread.start();
    }

    @Override
    public void run(){ // this run method is called by the Thread
        double drawInterval = 1000000000/FPS; 
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval; // find how much time has passed 
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint(); // calling paintComponent method
                delta--;
            }
        }
    }

    // Update information such as character positions
    public void update (){
        user.update(); //using update method from player class
    }

    public void paintComponent (Graphics g){
        super.paintComponent(g); 
        Graphics2D g2 = (Graphics2D)g; // more refined graphics

        // Title Screen
        if (gameScreen == titleScreen){
            ui.draw(g2);
        }
        else {
            //draw in the tiles
            tile.draw(g2);//calling draw method in TileManager class

            // draw in the player
            user.draw(g2); // using draw method from player class
            g2.dispose();

        }

    }

}