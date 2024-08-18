package entity.props;

import entity.Prop;
import main.GPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Prop_Campfire extends Prop {

    int animationStep = 0;
    BufferedImage[] animation = new BufferedImage[4];
    public Prop_Campfire(int x, int y, GPanel panel) throws IOException {
        this.x = x;
        this.y = y;
        sprite = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("props/campfire/tile000.png")));
        assetHeight = 18;
        assetWidth = 18;
        this.panel = panel;
        initAnim();
    }

    public void initAnim()
    {
        for(int i = 0; i < 4; i++)
        {
            try {
                animation[i] = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("props/campfire/tile00" + i + ".png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void draw(Graphics2D graphics) {

        //update animation every 20 frames
        if(panel.m_tickCount % 20 == 0)
        {
            animationStep++;
            if(animationStep > 3)
            {
                animationStep = 0;
            }
        }
        graphics.drawImage(animation[animationStep], x, y, assetWidth*panel.scale, assetHeight* panel.scale, panel);

    }
}
