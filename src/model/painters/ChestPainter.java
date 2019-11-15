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
            chestSprite = ImageIO.read(this.getClass().getResourceAsStream(CHEST_SPRITE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(BufferedImage im, Labyrinthe game) {
        im.getGraphics().drawImage(this.chestSprite, game.getObjects().getPosChest().getX()*5, game.getObjects().getPosChest().getY()*5, 20, 20, null);
    }
}
