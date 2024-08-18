package entity;

import main.GPanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Prop {
    protected int x;
    protected int y;
    protected BufferedImage sprite;
    protected int assetWidth, assetHeight;


    protected GPanel panel;

    public void update(){}
    public void draw(Graphics2D graphics){

        //render the sprite
        graphics.drawImage(sprite, x, y, assetWidth*panel.scale, assetHeight* panel.scale, panel);
    }



}
