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
    mapTileNum = new int[game.maxScreenCol][game.maxScreenRow];//storing all number from map text file

    getTileImage();
    loadMap();
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

    public void loadMap(){
        try{
            InputStream input = getClass().getResourceAsStream ("map.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            int column = 0;
            int row = 0;

            while(column < game.maxScreenCol && row < game.maxScreenRow){
                String line = reader.readLine(); //reading the text file (map)

                while (column < game.maxScreenCol){
                    String numbers[] = line.split(" ");//splits string around macthes of the regular expression

                    //changing string to integer
                    int num = Integer.parseInt(numbers[column]); //column is the index for numbers array
                    
                    mapTileNum[column][row] = num;
                    column++;
                }  
                //if max screen width is reached reset and move onto next row
                if(column == game.maxScreenCol){
                    column = 0;
                    row++;
                }
                reader.close();
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void draw (Graphics2D g2){

        int column = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(column < game.maxScreenCol && row < game.maxScreenRow){

            //CAN DELETE THIS COMMENT but everything in map is stored in mapTileNume array
            int tileNum = mapTileNum[column][row];//extracting a tile mumber stored in mapTile[0][0]

            g2.drawImage(tile[tileNum].image, x, y, game.tileSize, game.tileSize, null);
            
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
