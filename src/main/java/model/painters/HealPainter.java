package model.painters;

import model.Labyrinthe;
import model.Position;
import util.Constants;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class HealPainter implements Painter{

    private BufferedImage healSprite;

    public HealPainter(){
        try {
            healSprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(Constants.HEAL_SPRITE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(BufferedImage im, Labyrinthe game) {
        for (Position position:game.getPosHeals()) {
            im.getGraphics().drawImage(this.healSprite, position.getX()*5, position.getY()*5, 20, 20, null);
        }
    }
}
