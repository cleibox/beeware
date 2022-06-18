/**
 * Main
 * Desc: Sets up window function and runs program
 * @author Cynthia L & Phoebe Y ICS4U
 * @version 1.2 Jun 2022
 */

package main;

// IMPORTS (java swing)
import javax.swing.JFrame;

public class Main {
   public static void main(String[] args) {
      // ----------------------------------------------------------|
      // WINDOW SETUP ---------------------------------------------|
      // ----------------------------------------------------------|
      JFrame window = new JFrame();
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allows the window to close properly
      window.setResizable(false);
      window.setTitle("BEEware Of the Garden");
      
      GamePanel game = new GamePanel(); // initialize a gamepanel
      window.add(game);
      window.pack();// allows window to be sizeded to fit prefered size

      window.setLocationRelativeTo(null); // window will be displyed at the center of the screen
      window.setVisible(true);

      // ----------------------------------------------------------|
      // ASSET SPAWNER SETUP --------------------------------------|
      // ----------------------------------------------------------|
      // game.aSpawner.spawnTulip();
      // game.aSpawner.spawnNightshade();
      // game.aSpawner.spawnBee();

      // ----------------------------------------------------------|
      // GAME SETUP -----------------------------------------------|
      // ----------------------------------------------------------|
      game.startGameThread(); // the game loop starts here
      game.setupGame();
   }
}
