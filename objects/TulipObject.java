 /**
  * TulipObject
  * Desc: Tulip flower properties
  * @author Cynthia L & Phoebe Y ICS4U
  * @version 1.2 Jun 2022
  */

package objects;

import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;

public class TulipObject extends SuperObject {
    GamePanel game;
    public TulipObject(GamePanel game) {
        this.game = game;
        name = "Tulip";

        try { // in case there are image errors
            image = ImageIO.read(getClass().getResourceAsStream("/objects/tulip.png"));
            uTool.scaleImage(image, game.tileSize, game.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
