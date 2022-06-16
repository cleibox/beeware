 /**
  * TileManager
  * Desc: Manages and runs tile funtions
  * @author Cynthia L & Phoebe Y ICS4U
  * @version 1.2 Jun 2022
  */

package tile;

import main.GamePanel;

import java.awt.Graphics2D;
import java.io.BufferedReader;
// the following imports are needed for pictures
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

public class TileManager {
    
    GamePanel game;
    Tile[] tile;
    int mapTileNum[][];

    //tile constructor
    public TileManager (GamePanel game){
    this.game = game;
    tile = new Tile[10];//create 10 kinds of tiles
    mapTileNum = new int[game.maxMapCol][game.maxMapRow];//storing all number from map text file

    getTileImage();
    loadMap("map.txt");
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

            //bush tile
            tile[3] = new Tile ();
            tile[3].image = ImageIO.read(new File ("images/bush.png"));

            //dirt tile
            tile[4] = new Tile ();
            tile[4].image = ImageIO.read(new File ("images/dirt.png"));

            //sand tile
            tile[5] = new Tile ();
            tile[5].image = ImageIO.read(new File ("images/sand.png"));


        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public void loadMap(String filePath){
        try{
            InputStream input = getClass().getResourceAsStream (filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            int column = 0;
            int row = 0;

            while(column < game.maxMapCol && row < game.maxMapRow){
                String line = reader.readLine(); //reading the text file (map)

                while (column < game.maxMapCol){
                    String numbers[] = line.split(" ");//splits string around macthes of the regular expression

                    //changing string to integer
                    int num = Integer.parseInt(numbers[column]); //column is the index for numbers array
                    
                    mapTileNum[column][row] = num;
                    column++;
                }  
                //if max screen width is reached reset and move onto next row
                if(column == game.maxMapCol){
                    column = 0;
                    row++;
                }
            }
            reader.close();

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void draw (Graphics2D g2){

        int mapColumn = 0;
        int mapRow = 0;

        while(mapColumn < game.maxMapCol && mapRow < game.maxMapRow){

            //CAN DELETE THIS COMMENT but everything in map is stored in mapTileNume array
            int tileNum = mapTileNum[mapColumn][mapRow];//extracting a tile mumber stored in mapTile[0][0]
            
            //player position on MAP
            int mapX = mapColumn * game.tileSize;
            int mapY = mapRow * game.tileSize;

            //player position on SCREEN           (below is offset)
            int screenX = mapX - game.user.mapX + game.user.screenX;
            int screenY = mapY - game.user.mapY + game.user.screenY;

            //only draw tiles within screen (not entire map) --> more efficient, less glitchy
            if (((mapX + game.tileSize) > (game.user.mapX - game.user.screenX)) && 
                ((mapX - game.tileSize) < (game.user.mapX + game.user.screenX)) &&
                ((mapY + game.tileSize) > (game.user.mapY - game.user.screenY)) &&
                ((mapY - game.tileSize) < (game.user.mapY + game.user.screenY))){
            g2.drawImage(tile[tileNum].image, screenX, screenY, game.tileSize, game.tileSize, null);
            }

            //drawing the next tile
            mapColumn++;

            //if max map width is reached reset and move onto next row
            if(mapColumn == game.maxMapCol){
                mapColumn = 0;
                mapRow++;
            }

        }

    }

}
