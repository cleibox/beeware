 /**
  * NightshadeObject
  * Desc: Nightshade flower properties
  * @author Cynthia L & Phoebe Y ICS4U
  * @version 1.2 Jun 2022
  */

  package objects;

  import java.io.IOException;
  import javax.imageio.ImageIO;
  
  public class NightshadeObject extends SuperObject {
      public NightshadeObject() {
          name = "Nightshade";
  
          try { // in case there are image errors
              image = ImageIO.read(getClass().getResourceAsStream("/objects/nightshade.png"));
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
  }
  