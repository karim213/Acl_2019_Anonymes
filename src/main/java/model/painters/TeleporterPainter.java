package model.painters;

import model.Labyrinthe;
import model.Position;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static util.Constants.TELEPORTER_SPRITE;

public class TeleporterPainter implements Painter {
    private BufferedImage teleporterSprite;

    public TeleporterPainter(){
        try {
            teleporterSprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(TELEPORTER_SPRITE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(BufferedImage im, Labyrinthe game) {
        for (Position position:game.getObjects().getPosTeleporter()) {
            im.getGraphics().drawImage(this.teleporterSprite, position.getX()*5, position.getY()*5, 20, 20, null);
        }
    }
}
