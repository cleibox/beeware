/**
 * CollisionDetection
 * Desc: Checking for collisions in the game.
 * @author Cynthia L & Phoebe Y ICS4U
 * @version 1.2 Jun 2022
 */

package main;

// IMPORTS (classes)
import objects.SuperObject;
import sprites.Sprites;

public class CollisionDetection {
    GamePanel game;

    public CollisionDetection(GamePanel game) {
        this.game = game;
    }

    // ----------------------------------------------------------|
    // TILE COLLISION SETTINGS ----------------------------------|
    // ----------------------------------------------------------|
    public void tileDetection(Sprites sprites) {
        // ----------------------------------------------------------|
        // SPRITE COLLISION AREA ------------------------------------|
        // ----------------------------------------------------------|
        // All sprites should not collide into uncollidable tiles
        // Sprite collision area is not the sprite image area because that is too much collision area
        int spritesLeft = sprites.mapX + sprites.solidArea.x; // top left of collision area
        int spritesRight = sprites.mapX + sprites.solidArea.x + sprites.solidArea.width; // top right of collision area
        int spritesTop = sprites.mapY + sprites.solidArea.y; // top horizontal line of collision area
        int spritesBottom = sprites.mapY + sprites.solidArea.y + sprites.solidArea.height; // bottom horizontal line of collision area 

        // This finds the tiles (column & row) the player's collision area is on 
        int spritesLeftCol = spritesLeft / game.tileSize;
        int spritesRightCol = spritesRight / game.tileSize;
        int spritesTopRow = spritesTop / game.tileSize;
        int spritesBottomRow = spritesBottom / game.tileSize;

        // only 2 tiles need to be checked in each direction for collisions
        int tile1;
        int tile2;

        // ----------------------------------------------------------|
        // PLAYER DIRECTION COLLISION -------------------------------|
        // ----------------------------------------------------------|
        if (sprites.direction.equals("up")) {
            // predicting player location after moving UP
            spritesTopRow = (spritesTop - sprites.speed) / game.tileSize;

            // predicting tile number after movement
            tile1 = game.tileManager.mapTileNum[spritesLeftCol][spritesTopRow];// top left of solid area on player
            tile2 = game.tileManager.mapTileNum[spritesRightCol][spritesTopRow];// top right of solid area on player

            if ((game.tileManager.tile[tile1].collision == true) || (game.tileManager.tile[tile2].collision == true)) {
                sprites.collided = true;
            }
        } else if (sprites.direction.equals("down")) {
            // predicting player location after moving DOWN
            spritesBottomRow = (spritesBottom + sprites.speed) / game.tileSize;

            // predicting tile number after movement
            tile1 = game.tileManager.mapTileNum[spritesLeftCol][spritesBottomRow];// bottom left of solid area on player
            tile2 = game.tileManager.mapTileNum[spritesRightCol][spritesBottomRow];// bottom right of solid area on player

            if ((game.tileManager.tile[tile1].collision == true) || (game.tileManager.tile[tile2].collision == true)) {
                sprites.collided = true;
            }
        } else if (sprites.direction.equals("left")) {
            // predicting player location after moving LEFT
            spritesLeftCol = (spritesLeft - sprites.speed) / game.tileSize;

            // predicting tile number after movement
            tile1 = game.tileManager.mapTileNum[spritesLeftCol][spritesTopRow];// top left of solid area on player
            tile2 = game.tileManager.mapTileNum[spritesLeftCol][spritesBottomRow];// bottom left of solid area on player

            if ((game.tileManager.tile[tile1].collision == true) || (game.tileManager.tile[tile2].collision == true)) {
                sprites.collided = true;
            }
        } else if (sprites.direction.equals("right")) {
            // predicting player location after moving RIGHT
            spritesRightCol = (spritesRight + sprites.speed) / game.tileSize;

            // predicting tile number after movement
            tile1 = game.tileManager.mapTileNum[spritesRightCol][spritesTopRow];// top right of solid area on player
            tile2 = game.tileManager.mapTileNum[spritesRightCol][spritesBottomRow];// bottom right of solid area on player

            if ((game.tileManager.tile[tile1].collision == true) || (game.tileManager.tile[tile2].collision == true)) {
                sprites.collided = true;
            }
        }
    }

    // ----------------------------------------------------------|
    // PLAYER-OBJECT-BEE COLLISION SETTINGS ---------------------|
    // ----------------------------------------------------------|
    public void checkPlayerBeeCollision(Sprites sprites) {
        // get the sprite's solid area position
        sprites.solidArea.x = sprites.mapX + sprites.solidArea.x;
        sprites.solidArea.y = sprites.mapY + sprites.solidArea.y;

        // get the user's solid area position
        game.user.solidArea.x = game.user.mapX + game.user.solidArea.x;
        game.user.solidArea.y = game.user.mapY + game.user.solidArea.y;

        if (sprites.solidArea.intersects(game.user.solidArea)) {
            System.out.println("COLLIDE");
            game.user.playerHealth -= 1;
            // sprites.collided = true;
        }

        sprites.solidArea.x = sprites.solidAreaDefaultX;
        sprites.solidArea.y = sprites.solidAreaDefaultY;
        game.user.solidArea.x = game.user.solidAreaDefaultX;
        game.user.solidArea.y = game.user.solidAreaDefaultY;
    }
    

     // Checks if the sprite is hitting any objects (flowers)
     public int checkSpriteObjectCollision(Sprites sprites, SuperObject[] target) {
        int index = 999;

        for (int i = 0; i < target.length; i++) {
            if (target[i] != null) { // ignore nonexistent objects or bees
                // ----------------------------------------------------------|
                // ACCESS SOLID AREA ----------------------------------------|
                // ----------------------------------------------------------|
                // get the sprite's solid area posotion
                sprites.solidArea.x = sprites.mapX + sprites.solidArea.x;
                sprites.solidArea.y = sprites.mapY + sprites.solidArea.y;

                // get the object's solid area position
                target[i].solidArea.x = target[i].mapX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].mapY + target[i].solidArea.y;

                // ----------------------------------------------------------|
                // SPRITE DIRECTION COLLISION SETTINGS ----------------------|
                // ----------------------------------------------------------|
                if (sprites.direction.equals("up")) {
                    sprites.solidArea.y -= sprites.speed;
                    if (sprites.solidArea.intersects(target[i].solidArea)) { // sprite collides into object 
                        sprites.collided = true;
                        index = i;
                        
                    }
                } else if (sprites.direction.equals("down")) {
                    sprites.solidArea.y += sprites.speed;
                    if (sprites.solidArea.intersects(target[i].solidArea)) {
                        sprites.collided = true;
                        index = i;
                    }
                } else if (sprites.direction.equals("left")) {
                    sprites.solidArea.x -= sprites.speed;
                    if (sprites.solidArea.intersects(target[i].solidArea)) {
                        sprites.collided = true;
                        index = i;
                    }
                } else if (sprites.direction.equals("right")) {
                    sprites.solidArea.x += sprites.speed;
                    if (sprites.solidArea.intersects(target[i].solidArea)) {
                        sprites.collided = true;
                        index = i;
                    }
                }
                // Refresh player and object solid area 
                sprites.solidArea.x = sprites.solidAreaDefaultX;
                sprites.solidArea.y = sprites.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }
        return index;
    }
}
