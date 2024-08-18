package Logic;

import Renderer.Tile;
import entity.*;
import main.GPanel;

import java.awt.*;

public class CollisionManager {
    GPanel panel;

    public CollisionManager(GPanel panel) {
        this.panel = panel;
    }

   /* public void checkTile(Entity entity) {
        int entLeftX = entity.x + entity.hitbox.x;
        int entRightX = entity.x + entity.hitbox.x + entity.hitbox.width;
        int entTopY = entity.y + entity.hitbox.y;
        int entBottomY = entity.y + entity.hitbox.y + entity.hitbox.height;

        int entityLeftCol = entLeftX / panel.tileSize;
        int entityRightCol = entRightX / panel.tileSize;
        int entityTopRow = entTopY / panel.tileSize;
        int entityBottomRow = entBottomY / panel.tileSize;

       switch(entity.dir)
       {
           case direction.UP:
              entityTopRow = (entTopY - entity.speed) / panel.tileSize;
                entityBottomRow = (entBottomY - entity.speed) / panel.tileSize;
                int tile1, tile2;
                tile1 = panel.t_renderer.tileNumbers[entityTopRow][entityLeftCol];
                tile2 = panel.t_renderer.tileNumbers[entityTopRow][entityRightCol];
                if(panel.t_renderer.tiles[tile1].blocked || panel.t_renderer.tiles[tile2].blocked)
                {

                    entity.collides = true;
                }

               break;
              case direction.DOWN:
                entityTopRow = (entTopY + entity.speed) / panel.tileSize;
                entityBottomRow = (entBottomY + entity.speed) / panel.tileSize;
                tile1 = panel.t_renderer.tileNumbers[entityBottomRow][entityLeftCol];
                tile2 = panel.t_renderer.tileNumbers[entityBottomRow][entityRightCol];
                if(panel.t_renderer.tiles[tile1].blocked || panel.t_renderer.tiles[tile2].blocked)
                {
                    entity.collides = true;
                }
               break;
                case direction.LEFT:
                    entityLeftCol = (entLeftX - entity.speed) / panel.tileSize;
                    entityRightCol = (entRightX - entity.speed) / panel.tileSize;
                    tile1 = panel.t_renderer.tileNumbers[entityTopRow][entityLeftCol];
                    tile2 = panel.t_renderer.tileNumbers[entityBottomRow][entityLeftCol];
                    if(panel.t_renderer.tiles[tile1].blocked || panel.t_renderer.tiles[tile2].blocked)
                    {
                        entity.collides = true;
                    }
                break;
                case direction.RIGHT:
                    entityLeftCol = (entLeftX + entity.speed) / panel.tileSize;
                    entityRightCol = (entRightX + entity.speed) / panel.tileSize;
                    tile1 = panel.t_renderer.tileNumbers[entityTopRow][entityRightCol];
                    tile2 = panel.t_renderer.tileNumbers[entityBottomRow][entityRightCol];
                    if(panel.t_renderer.tiles[tile1].blocked || panel.t_renderer.tiles[tile2].blocked)
                    {
                        entity.collides = true;
                    }
                break;
           default:
               break;

       }


    }*/

    public boolean checkTile(Rectangle hitbox)
    {
        if(!panel.t_renderer.initalized)
        {
            return false;
        }
        //check for collision
        int leftX = hitbox.x;
        int rightX = hitbox.x + hitbox.width;
        int topY = hitbox.y;
        int bottomY = hitbox.y + hitbox.height;

        int leftCol = (leftX / panel.t_renderer.renderSize );
        int rightCol = (rightX / panel.t_renderer.renderSize);
        int topRow = (topY / panel.t_renderer.renderSize);
        int bottomRow = (bottomY / panel.t_renderer.renderSize);
        //account for rounding errors
        if(rightCol >= panel.t_renderer.mapWidth)
        {
            rightCol = panel.t_renderer.mapWidth - 1;
        }
        if(bottomRow >= panel.t_renderer.mapHeight)
        {
            bottomRow = panel.t_renderer.mapHeight - 1;
        }
        if(leftCol >= panel.t_renderer.mapWidth)
        {
            leftCol = panel.t_renderer.mapWidth - 1;
        }
        if(topRow >= panel.t_renderer.mapHeight)
        {
            topRow = panel.t_renderer.mapHeight - 1;
        }


        int tile1, tile2, tile3, tile4;
        tile1 = panel.t_renderer.tileNumbers[topRow][leftCol];
        tile2 = panel.t_renderer.tileNumbers[topRow][rightCol];
        tile3 = panel.t_renderer.tileNumbers[bottomRow][leftCol];
        tile4 = panel.t_renderer.tileNumbers[bottomRow][rightCol] ;
//ignore if any are null

        return checkIfBlocked(tile1) || checkIfBlocked(tile2) || checkIfBlocked(tile3) || checkIfBlocked(tile4);
    }

    boolean checkIfBlocked(int tile)
    {
        if(panel.t_renderer.tiles[tile] == null)
        {
            return false;
        }
        return panel.t_renderer.tiles[tile].blocked;
    }

}
