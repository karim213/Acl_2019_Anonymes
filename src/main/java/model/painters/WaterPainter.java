package model.painters;

import model.Labyrinthe;
import model.Position;
import util.Constants;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class WaterPainter implements Painter {
    private BufferedImage waterSprite;

    public WaterPainter(){
        try {
            waterSprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(Constants.WATER_SPRITE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(BufferedImage im, Labyrinthe game) {
        for (Position position:game.getPosWaters()) {
            im.getGraphics().drawImage(this.waterSprite, position.getX()*5, position.getY()*5, 20, 20, null);
        }
    }
}
