package tile;

import main.GamePanel;

import java.awt.Graphics2D;

// the following imports are needed for pictures
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TileManager {
    
    GamePanel game;
    Tile[] tile;

    //tile constructor
    public TileManager (GamePanel game){
    this.game = game;
    tile = new Tile[10];//create 10 kinds of tiles

    getTileImage();
    }

    //method to import images
    public void getTileImage (){

        try{

            //grass tile
            tile[0] = new Tile ();
            tile[0].image = ImageIO.read(new File ("images/grass.png"));

            //brick tile tile
            tile[1] = new Tile ();
            tile[1].image = ImageIO.read(new File ("images/brick.png"));

            //stone tile
            tile[2] = new Tile ();
            tile[2].image = ImageIO.read(new File ("images/stone.png"));


        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    public void draw (Graphics2D g2){

        int column = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(column < game.maxScreenCol && row < game.maxScreenRow){
            g2.drawImage(tile[0].image , x, y, game.tileSize, game.tileSize, null);
            
            //drawing the next tile
            column++;
            x += game.tileSize;

            //if max screen width is reached reset and move onto next row
            if(column == game.maxScreenCol){
                column = 0;
                x = 0;
                row++;
                y += game.tileSize;
            }

        }

        //g2.drawImage(tile[0].image, 0, 0, game.tileSize, game.tileSize, null);
        //g2.drawImage(tile[1].image, 48, 0, game.tileSize, game.tileSize, null);
        //g2.drawImage(tile[2].image, 96, 0, game.tileSize, game.tileSize, null);


    }

}
