package entity.props;

import entity.Prop;
import main.GPanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;


public class Prop_Bedroll extends Prop {
    public Prop_Bedroll(int x, int y, GPanel panel) throws IOException {
        this.x = x;
        this.y = y;
        sprite = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("props/bedroll.png")));
        assetHeight = 20;
        assetWidth = 20;
        this.panel = panel;
    }
}
