package Renderer;

import java.awt.image.BufferedImage;

public class SpriteRenderUtils {
public static BufferedImage getSprite (BufferedImage sheet, int idx, int width, int height){
        int x = idx % (sheet.getWidth() / width) * width;
        int y = idx / (sheet.getWidth() / width) * height;
        return sheet.getSubimage(x, y, width, height);
    }

    public static BufferedImage flipSprite(BufferedImage sprite){
        int w = sprite.getWidth();
        int h = sprite.getHeight();
        BufferedImage flipped = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        for(int xx = 0; xx < w; xx++){
            for(int yy = 0; yy < h; yy++){
                flipped.setRGB(w-xx-1, yy, sprite.getRGB(xx, yy));
            }
        }
        return flipped;

    }


}
