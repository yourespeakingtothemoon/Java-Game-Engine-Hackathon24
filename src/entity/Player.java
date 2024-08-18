package entity;

import javax.imageio.ImageIO;

import Renderer.SpriteRenderUtils;
import main.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{
    GPanel panel;
    InputManager input;

    public int[] walkSprites = {9,10,11};
    public int[] idleSprites = {3,5,6,7};
    public int[] hurtSprites = {24,25,26};
    public int[] dyingSprites = {27,28,29,30};
    private BufferedImage spriteSheet;

    int Velocity =0;

    public Player(){
    }
    boolean flippedWalk = false;
    public Player(GPanel panel, InputManager input){
        this.panel = panel;
        this.input = input;

        wallBox = new Rectangle(9*3,16*3,5*3,3);
    }

    public void init(){
        initalizeDefault();
        initGraphics(32, 32);
    }
    public void init(int w, int h){
        initalizeDefault();
        initGraphics(w, h);
    }
    public void initalizeDefault(){
        worldX = 725;
        worldY = 350;
        screenX = panel.width/2 - (panel.tileSize/2);
        screenY = panel.height/2 - (panel.tileSize/2);
        speed = 5;

    }
    public void initGraphics(int w, int h){
        spriteWidth = w;
        spriteHeight = h;
        try {
            spriteSheet = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("player/player-sheet.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    @Override
    public void update(){
        if(input.up){
            worldY -= speed;
            dir = direction.UP;
            //anim_state = animationState.WALK;
        }
        if(input.down){
            worldY += speed;
            dir = direction.DOWN;
            //anim_state = animationState.WALK;
        }
        if(input.left){
            worldX -= speed;
            flippedWalk = true;
            dir = direction.LEFT;
            //anim_state = animationState.WALK;
        }
        if(input.right){
            worldX += speed;
            flippedWalk = false;
            dir = direction.RIGHT;
            //anim_state = animationState.WALK;
        }
        wallBox.x = worldX + (9*3);
        wallBox.y = worldY + (14*3);

        collides = panel.m_collision.checkTile(wallBox);
        if(collides){
            //stop player from moving in that direction

            if(dir == direction.UP){
                worldY += speed;
            }
            if(dir == direction.DOWN){
                worldY -= speed;
            }
            if(dir == direction.LEFT){
                worldX += speed;
            }
            if(dir == direction.RIGHT){
                worldX -= speed;
            }


        }
    }

    @Override
    public void draw(Graphics2D graphics){
        if(spriteSheet == null){
            return;
        }
        BufferedImage sprite = null;
        if(input.up || input.down || input.left || input.right){
            sprite =  SpriteRenderUtils.getSprite(spriteSheet,walkSprites[(int) ((System.currentTimeMillis() / 200) % walkSprites.length)], 22, 22);
        } else {
         sprite = SpriteRenderUtils.getSprite(spriteSheet,idleSprites[(int) ((System.currentTimeMillis() / 200) % idleSprites.length)], 22, 22);
        }
        //flip sprite if going left
        if(flippedWalk){
            sprite = SpriteRenderUtils.flipSprite(sprite);
        }

        graphics.drawImage(sprite, worldX, worldY, spriteWidth, spriteHeight, null);
        //draw hitbox
        graphics.setColor(Color.BLUE);

        //graphics.drawRect(wallBox.x, wallBox.y, wallBox.width, wallBox.height);
    }


}
