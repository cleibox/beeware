/* BeewareOfTheGarden
 * Desc: INSERT
 * @Cynthia L & Phoebe Y ICS4U
 * @version Jun 2022
 */

 package main;
 import javax.swing.JFrame;

 public class Main{
     public static void main (String [] args){
         
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //allows the window to close properly
        window.setResizable (false);
        window.setTitle ("BEEware Of the Garden");
        
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();//allows window to be sizeded to fit prefered size

        window.setLocationRelativeTo(null); //window will be displyed at the center of the screen 
        window.setVisible(true);

        gamePanel.startGameThread();

     }
    }


