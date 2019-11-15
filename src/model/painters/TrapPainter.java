package model.painters;

import model.Labyrinthe;
import model.Position;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static util.Constants.TRAP_SPRITE;

public class TrapPainter implements Painter{

    private BufferedImage trapSprite;

    public TrapPainter(){
        try {
            trapSprite = ImageIO.read(this.getClass().getResourceAsStream(TRAP_SPRITE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(BufferedImage im, Labyrinthe game) {
        for (Position position:game.getObjects().getPosTraps()) {
            im.getGraphics().drawImage(this.trapSprite, position.getX()*5, position.getY()*5, 20, 20, null);
        }
    }
}
