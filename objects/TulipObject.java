 /**
  * TulipObject
  * Desc: Tulip flower properties
  * @author Cynthia L & Phoebe Y ICS4U
  * @version 1.2 Jun 2022
  */

package objects;

import java.io.IOException;
import javax.imageio.ImageIO;

public class TulipObject extends SuperObject {
    public TulipObject() {
        name = "Tulip";

        try { // in case there are image errors
            image = ImageIO.read(getClass().getResourceAsStream("/objects/tulip.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
