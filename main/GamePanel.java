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

public class GamePanel extends JPanel implements Runnable{
    // Screen settings
    final int originalTileSize = 16; // 16 x 16 tile
    final int scale = 3;

    final int tileSize = originalTileSize * scale; // 48 x 48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // setting FPS (Frames per Second)
    int FPS = 60;

    MyKeyListener key = new MyKeyListener();
    Thread gameThread;// once started, keeps game running

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
        if (key.upPressed == true){
            playerY -= playerSpeed;
        }
        else if (key.downPressed == true){
            playerY += playerSpeed;
        }
        else if (key.leftPressed == true){
            playerX -= playerSpeed;
        }
        else if (key.rightPressed == true){
            playerX += playerSpeed;
        }
    }

    public void paintComponent (Graphics g){
        super.paintComponent(g); 
        Graphics2D g2 = (Graphics2D)g;// more refined graphics 

        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();
    }


}