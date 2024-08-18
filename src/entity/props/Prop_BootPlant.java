package entity.props;

import entity.Prop;
import main.GPanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Prop_BootPlant extends Prop {
    public Prop_BootPlant(int x, int y, GPanel panel) throws IOException {
        this.x = x;
        this.y = y;
        sprite = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("props/boot-plant.png")));
        assetHeight = 12;
        assetWidth = 12;
        this.panel = panel;
    }
}
