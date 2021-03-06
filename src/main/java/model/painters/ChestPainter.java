package model.painters;

import model.Labyrinthe;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static util.Constants.CHEST_SPRITE;

public class ChestPainter implements Painter {

    private BufferedImage chestSprite;

    public ChestPainter(){
        try {
            chestSprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(CHEST_SPRITE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(BufferedImage im, Labyrinthe game) {
        im.getGraphics().drawImage(this.chestSprite, game.getPosChest().getX()*5, game.getPosChest().getY()*5, 20, 20, null);
    }
}
