 /**
  * MyKeyListener
  * Desc: Sets up and runs key functions
  * @author Cynthia L & Phoebe Y ICS4U
  * @version 1.2 Jun 2022
  */

package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyListener implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {
        // not used
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();// returns integer associated with each key

        if (code == KeyEvent.VK_UP) { // if 'up arrow' is pressed
        upPressed = true;
        }

        if (code == KeyEvent.VK_DOWN) { // if 'down arrow' is pressed
        downPressed = true;
        }

        if (code == KeyEvent.VK_LEFT) { // if 'left arrow' is pressed
        leftPressed = true;
        }

        if (code == KeyEvent.VK_RIGHT) { // if 'right arrow' is pressed
        rightPressed = true;
        }

    }
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();// returns integer associated with each key
       
        if (code == KeyEvent.VK_UP) { // if 'up arrow' is pressed
        upPressed = false;
        }

        if (code == KeyEvent.VK_DOWN) { // if 'down arrow' is pressed
        downPressed = false;
        }

        if (code == KeyEvent.VK_LEFT) { // if 'left arrow' is pressed
        leftPressed = false;
        }

        if (code == KeyEvent.VK_RIGHT) { // if 'right arrow' is pressed
        rightPressed = false;
        }
    }
}
