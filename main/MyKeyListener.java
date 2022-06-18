/**
 * MyKeyListener
 * Desc: Sets up and runs key functions
 * @author Cynthia L & Phoebe Y ICS4U
 * @version 1.2 Jun 2022
 */

package main;

// IMPORTS (key inputs)
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyListener implements KeyListener {
    GamePanel game;
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    public MyKeyListener(GamePanel game) {
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {} // not used 

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); // returns the integer associated with each key

        // ----------------------------------------------------------|
        // TITLE SCREEN INPUT ---------------------------------------|
        // ----------------------------------------------------------|
        if (game.gameScreen == game.titleScreen) {
            if (game.ui.rulesScreen == 0) { // on the title screen; lack of rules screen
                if (code == KeyEvent.VK_UP) { // if 'up arrow' is pressed
                    game.ui.commandNum--;
                    if (game.ui.commandNum < 0) { // let the choosing arrow loop through the choices
                        game.ui.commandNum = 2;
                    }
                }

                if (code == KeyEvent.VK_DOWN) { // if 'down arrow' is pressed
                    game.ui.commandNum++;
                    if (game.ui.commandNum > 2) {
                        game.ui.commandNum = 0;
                    }
                }

                if (code == KeyEvent.VK_ENTER) {
                    if (game.ui.commandNum == 0) { // chose "PLAY"
                        game.gameScreen = game.playScreen;
                        System.out.println("gameee " + game.gameScreen);
                    }
                    if (game.ui.commandNum == 1) { // chose "RULES"
                        game.ui.rulesScreen = 1;
                    }
                    if (game.ui.commandNum == 2) { // chose "QUIT"
                        System.exit(0);
                    }
                }
            } else if (game.ui.rulesScreen == 1) { // on the rules screen
                if (code == KeyEvent.VK_ENTER) {
                    game.ui.rulesScreen = 0;
                }
            }
        }

        // ----------------------------------------------------------|
        // GAME SCREEN INPUT ----------------------------------------|
        // ----------------------------------------------------------|
        if (game.gameScreen == game.playScreen) {
            if (code == KeyEvent.VK_UP) { // if 'up arrow' is pressed
                upPressed = true;
                // game.playSoundEffect(4); // walk collection sound effect
            }

            if (code == KeyEvent.VK_DOWN) { // if 'down arrow' is pressed
                downPressed = true;
                game.playSoundEffect(4); // walk collection sound effect
            }

            if (code == KeyEvent.VK_LEFT) { // if 'left arrow' is pressed
                leftPressed = true;
                game.playSoundEffect(4); // walk collection sound effect
            }

            if (code == KeyEvent.VK_RIGHT) { // if 'right arrow' is pressed
                rightPressed = true;
                game.playSoundEffect(4); // walk collection sound effect
            }
            if (code == KeyEvent.VK_ESCAPE) { // if 'escape' key is pressed
                System.exit(0); // quit
            }
        }

        // ----------------------------------------------------------|
        // END SCREEN INPUT -----------------------------------------|
        // ----------------------------------------------------------|
        if (game.gameScreen == game.endScreen) {
            if (code == KeyEvent.VK_ENTER) { // if 'enter' key is pressed
                System.exit(0); // quit 
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();// returns integer associated with each key

        if (code == KeyEvent.VK_UP) { // if 'up arrow' was previously pressed
            upPressed = false;
        }

        if (code == KeyEvent.VK_DOWN) { // if 'down arrow' was previously pressed
            downPressed = false;
        }

        if (code == KeyEvent.VK_LEFT) { // if 'left arrow' was previously pressed
            leftPressed = false;
        }

        if (code == KeyEvent.VK_RIGHT) { // if 'right arrow' was previously pressed
            rightPressed = false;
        }
    }
}
