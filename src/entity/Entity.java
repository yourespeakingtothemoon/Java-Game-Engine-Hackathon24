package entity;

import java.awt.*;

public class Entity {
    public int worldX, worldY, screenX, screenY, speed;
    public int spriteWidth, spriteHeight;
    public Rectangle wallBox;
    public boolean collides = false;
    public direction dir = direction.NONE;



    protected animationState anim_state = animationState.IDLE;

    public Entity(){
        worldX = 0;
        worldY = 0;
        speed = 0;
    }

    public void update(){}
    public void draw(Graphics2D graphics){}

}
