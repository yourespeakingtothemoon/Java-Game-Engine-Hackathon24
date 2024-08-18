package Renderer;

import main.GPanel;
import main.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileRenderer {
    GPanel panel;
    public Tile[] tiles;
    BufferedImage tileSheet;
    String mapFile;
    String collisionFile;
    public int mapWidth=0;
    public int mapHeight=0;

    int waterTileStep = 0;

    public boolean initalized = false;

    public int[][] tileNumbers;

    int assetSize = 22;
    public int renderSize = 22;

    public TileRenderer(GPanel panel, int width, int height) {
        this.panel = panel;
        tiles = new Tile[width * height];
    }

    public TileRenderer(GPanel panel, int width, int height, String mapFile, String tileSheet, int asset_size, int rend_size, String collisionFile) {
        this.panel = panel;
        tiles = new Tile[width * height];
        this.mapFile = mapFile;
        this.mapWidth = width;
        this.mapHeight = height;
        getTileSheet(tileSheet);
        this.assetSize = asset_size;
        this.renderSize = rend_size;
        this.collisionFile = collisionFile;
        tileNumbers = new int[panel.maxWorldColumns][panel.maxWorldRows];
    }


    public void getTileSheet(String filename) {
        // Load tile sheet
        try{
            tileSheet = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("tilemaps/" + filename)));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void draw(Graphics2D graphics) {
        int[] mapData = Utils.readMapData(mapFile, mapHeight, mapWidth);
        int[] collisionData = Utils.readMapData(collisionFile, mapHeight, mapWidth);
        for (int i = 0; i < mapData.length; i++) {
            int idx = mapData[i];
            Tile tile = tiles[i];
            if (tile == null) {
                tile = new Tile();
                tile.blocked = collisionData[i] == 1;
                tiles[i] = tile;
            }
                tile.sprite = SpriteRenderUtils.getSprite(tileSheet, animateWater(idx-1), assetSize, assetSize);

            tileNumbers[i / mapWidth][i % mapWidth] = i;

                graphics.drawImage(tile.sprite, (i % mapWidth) * renderSize, (i / mapWidth) * renderSize, renderSize, renderSize, null);

           /* if(tile.blocked)
            {
                graphics.setColor(Color.RED);
                graphics.fillRect((i % mapWidth) * renderSize, (i / mapWidth) * renderSize, renderSize, renderSize);
            }*/
        }
        initalized = true;
    //there are 60 gameticks in a second only change the water tile every 300 milliseconds

        if(panel.m_tickCount % 15 == 0) {
            waterTileStep = (waterTileStep + 1) % 4;
        }
      //slow this down



    }

    public void update() {
        int[] collisionData = Utils.readMapData(collisionFile, mapHeight, mapWidth);
        for (int i = 0; i < tiles.length; i++) {
            tiles[i].blocked = collisionData[i] == 1;
        }

    }

    int animateWater(int tileIndex){
       //if tile index is any of 111 113 114 115 133 135 136 137 155 157
        //return tileIndex + 1
        //else return tileIndex
        if(tileIndex == 111 || tileIndex == 113 || tileIndex == 114 || tileIndex == 115 || tileIndex == 133 || tileIndex == 135 || tileIndex == 136 || tileIndex == 137 || tileIndex == 155 || tileIndex==156 || tileIndex == 157)
        {
            return tileIndex + (5 * waterTileStep);
        }
        return tileIndex;
    }

}
