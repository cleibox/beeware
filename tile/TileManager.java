 /**
  * TileManager
  * Desc: Manages and runs tile funtions
  * @author Cynthia L & Phoebe Y ICS4U
  * @version 1.2 Jun 2022
  */

package tile;

import main.GamePanel;
import main.UtilityTool;

import java.awt.Graphics2D;
import java.io.BufferedReader;

// the following imports are needed for pictures
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import javax.swing.text.Utilities;

public class TileManager { 
    GamePanel game;
    public Tile[] tile;
    public int mapTileNum[][];
    // public int mapTileNumPath[][][];

    //tile constructor
    public TileManager (GamePanel game){
        this.game = game;

        tile = new Tile[10];//create 10 kinds of tiles
        mapTileNum = new int[game.maxMapCol][game.maxMapRow];//storing all number from map text file

        // mapTileNumPath = new int[game.maxMap][game.maxMapCol][game.maxMapRow];
        
        getTileImage();
        loadMap("map.txt");
    }

    //method to import images
    public void getTileImage (){
        //grass tile
        setup(0, "grass", false);

        //brick tile tile --> solid
        setup(1, "brick", true);

        //stone tile --> solid
        setup(2, "stone", true);

        //bush tile
        setup(3, "bush", false);

        //dirt tile
        setup(4, "dirt", false);

        //sand tile
        setup(5, "sand", false);

    }

    public void setup(int index, String imageName, boolean collision){
        UtilityTool uTool = new UtilityTool();

        try{
            tile[index] = new Tile();
            // tile[index].image = ImageIO.read(getClass().getResourceAsStream("images/" + imageName + ".png"));
            tile[index].image = ImageIO.read(new File ("images/" + imageName + ".png"));


            tile[index].image = uTool.scaleImage(tile[index].image, game.tileSize, game.tileSize);
            tile[index].collision = collision;
        } catch(IOException e) {
            e.printStackTrace();
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
            g2.drawImage(tile[tileNum].image, screenX, screenY, null);
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
