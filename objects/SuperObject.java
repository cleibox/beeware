/**
 * SuperObject
 * Desc: Class of all the objects (flowers)
 * @author Cynthia L & Phoebe Y ICS4U
 * @version 1.2 Jun 2022
 */

package objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import main.GamePanel;
import java.awt.Rectangle;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int mapX, mapY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48); // collision area
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public void draw(Graphics2D g2, GamePanel game) {
        // player position on SCREEN (below is offset)
        int screenX = mapX - game.user.mapX + game.user.screenX;
        int screenY = mapY - game.user.mapY + game.user.screenY;

        // only draw tiles within screen (not entire map) --> more efficient, less glitchy
        if (((mapX + game.tileSize) > (game.user.mapX - game.user.screenX)) &&
                ((mapX - game.tileSize) < (game.user.mapX + game.user.screenX)) &&
                ((mapY + game.tileSize) > (game.user.mapY - game.user.screenY)) &&
                ((mapY - game.tileSize) < (game.user.mapY + game.user.screenY))) {

            g2.drawImage(image, screenX, screenY, game.tileSize, game.tileSize, null);
        }
    }
}
