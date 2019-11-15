package model.painters;

import model.Labyrinthe;
import model.Position;
import util.Constants;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class HealPainter implements Painter{

    private BufferedImage trapSprite;

    public HealPainter(){
        try {
            trapSprite = ImageIO.read(this.getClass().getResourceAsStream(Constants.HEAL_SPRITE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(BufferedImage im, Labyrinthe game) {
        for (Position position:game.getObjects().getPosHeals()) {
            im.getGraphics().drawImage(this.trapSprite, position.getX()*5, position.getY()*5, 20, 20, null);
        }
    }
}
