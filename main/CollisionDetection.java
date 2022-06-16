 /**
  * CollisionDetection
  * Desc: Checking for collisions in the game.
  * @author Cynthia L & Phoebe Y ICS4U
  * @version 1.2 Jun 2022
  */

package main;

import sprites.Sprites;

public class CollisionDetection {

    GamePanel game;

    //constructor 
    public CollisionDetection (GamePanel game){
        this.game = game;
    }

    //collision detection method
    public void tileDetection (Sprites sprites){
        int spritesLeft = sprites.mapX + sprites.solid.x;
        int spritesRight = sprites.mapX + sprites.solid.x + sprites.solid.width;
        int spritesTop = sprites.mapY + sprites.solid.y;
        int spritesBottom = sprites.mapY + sprites.solid.y + sprites.solid.height;

        //column and row number on map
        int spritesLeftCol = spritesLeft/game.tileSize;
        int spritesRightCol = spritesRight/game.tileSize;
        int spritesTopRow = spritesTop/game.tileSize;
        int spritesBottomRow = spritesBottom/game.tileSize;

        //only 2 tiles need to be checked in each direction for collisions
        int tile1;
        int tile2;

        if (sprites.direction.equals ("up")){
            //predicting player location after moving UP
            spritesTopRow = (spritesTop - sprites.speed)/game.tileSize;

            //predicting tile number after movement
            tile1 = game.tile.mapTileNum[spritesLeftCol][spritesTopRow];//top left of solid area on player
            tile2 = game.tile.mapTileNum[spritesRightCol][spritesTopRow];//top right of solid area on player

            if((game.tile.tile[tile1].collision == true) || (game.tile.tile[tile2].collision == true) ){
                sprites.collided = true;
            }
        }else if(sprites.direction.equals ("down") ){
            //predicting player location after moving DOWN
            spritesBottomRow = (spritesBottom + sprites.speed)/game.tileSize;

            //predicting tile number after movement
            tile1 = game.tile.mapTileNum[spritesLeftCol][spritesBottomRow];//bottom left of solid area on player
            tile2 = game.tile.mapTileNum[spritesRightCol][spritesBottomRow];//bottom right of solid area on player

            if((game.tile.tile[tile1].collision == true) || (game.tile.tile[tile2].collision == true) ){
                sprites.collided = true;
            }
        } else if(sprites.direction.equals ("left") ){
            //predicting player location after moving LEFT
            spritesLeftCol = (spritesLeft - sprites.speed)/game.tileSize;

            //predicting tile number after movement
            tile1 = game.tile.mapTileNum[spritesLeftCol][spritesTopRow];//top left of solid area on player
            tile2 = game.tile.mapTileNum[spritesLeftCol][spritesBottomRow];//bottom left of solid area  on player

            if((game.tile.tile[tile1].collision == true) || (game.tile.tile[tile2].collision == true) ){
                sprites.collided = true;
            }
        }else if(sprites.direction.equals ("right") ){
            //predicting player location after moving RIGHT
            spritesRightCol = (spritesRight + sprites.speed)/game.tileSize;

            //predicting tile number after movement
            tile1 = game.tile.mapTileNum[spritesRightCol][spritesTopRow];//top right of solid area on player
            tile2 = game.tile.mapTileNum[spritesRightCol][spritesBottomRow];//bottom right of solid area on player

            if((game.tile.tile[tile1].collision == true) || (game.tile.tile[tile2].collision == true) ){
                sprites.collided = true;
            }
        }
    }
    
}
