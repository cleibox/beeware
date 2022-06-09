/* BeewareOfTheGarden
 * Desc: INSERT
 * @Cynthia L & Phoebe Y ICS4U
 * @version Jun 2022
 */
package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import javax.swing.JPanel;
import sprites.Player;

public class GamePanel extends JPanel implements Runnable{
    // Screen settings
    final int originalTileSize = 16; // 16 x 16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48 x 48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // setting FPS (Frames per Second)
    int FPS = 60;

    MyKeyListener key = new MyKeyListener();
    Thread gameThread;// once started, keeps game running
    Player user = new Player (this, key);

    // Set player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;


    public GamePanel () {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // set size of the JPanel
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);// imporve rendering
        this.addKeyListener(key);
        this.setFocusable (true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run(){

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null){

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/ drawInterval;
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint(); //calling paintComponent method
                delta--;
            }
        }
    }

    public void update (){
        user.update(); //using update method from player class
    }

    public void paintComponent (Graphics g){
        super.paintComponent(g); 
        Graphics2D g2 = (Graphics2D)g;// more refined graphics 
        user.draw(g2);// using draw method from player class
        g2.dispose();
    }


}