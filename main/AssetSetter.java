 /**
  * AssetSetter
  * Desc: Handles all the objects
  * @author Cynthia L & Phoebe Y ICS4U
  * @version 1.2 Jun 2022
  */

package main;

import objects.TulipObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AssetSetter {
    GamePanel game;
    int spawnX = 1;
    int spawnY = 1;    
    public int mapTileNum[][];
    
    public AssetSetter(GamePanel game){
        this.game = game;
        mapTileNum = new int[game.maxMapCol][game.maxMapRow];//storing all number from map text file
        System.out.println(mapTileNum[1][1]);
        loadMap("/tile/map.txt"); // locating the tile map file
    }
    
    public void setObject() {
        game.obj[0] = new TulipObject(); // tulip object is a sub class of SuperObject so we are able to instantiate it
        game.obj[0].mapX = getRandomWorldCoordinates()[0] * game.tileSize;
        game.obj[0].mapY = getRandomWorldCoordinates()[1] * game.tileSize;

        System.out.println("mapX " + (game.obj[0].mapX)/game.tileSize + " mapY " + (game.obj[0].mapY)/game.tileSize);
    }
    
    public int[] getRandomWorldCoordinates() {
        do {
            spawnX = (int)Math.floor(Math.random()*(48-1+1)+1);
            spawnY = (int)Math.floor(Math.random()*(48-1+1)+1);
        } while (mapTileNum[spawnX][spawnY] == 1 || mapTileNum[spawnX][spawnY] == 2); // Ensures the objects don't spawn at the bricks or stones 
        
        int[] spawnSet = {spawnX, spawnY};
        return spawnSet;
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
}
