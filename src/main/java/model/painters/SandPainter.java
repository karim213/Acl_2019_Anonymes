package model.painters;

import model.Labyrinthe;
import model.Position;
import util.Constants;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SandPainter implements Painter {
    private BufferedImage sandSprite;

    public SandPainter(){
        try {
            sandSprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(Constants.SAND_SPRITE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(BufferedImage im, Labyrinthe game) {
        for (Position position:game.getPosSands()) {
            im.getGraphics().drawImage(this.sandSprite, position.getX()*5, position.getY()*5, 20, 20, null);
        }
    }
}
