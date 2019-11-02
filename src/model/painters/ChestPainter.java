package model.painters;

import model.Labyrinthe;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ChestPainter implements Painter {

    private BufferedImage chestSprite;

    public ChestPainter(){
        try {
            chestSprite = ImageIO.read(new File("Ressources/chest.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(BufferedImage im, Labyrinthe game) {
        im.getGraphics().drawImage(this.chestSprite, game.getChest().getPosition().getX()*10+20, game.getChest().getPosition().getY()*5+40, 20, 20, null);
    }
}
