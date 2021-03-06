package model.painters;

import model.Labyrinthe;
import model.Position;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static util.Constants.WALL_SPRITE;

public class WallPainter implements Painter {

    private BufferedImage wallSprite;

    public WallPainter(){
        try {
            wallSprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(WALL_SPRITE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(BufferedImage im, Labyrinthe game) {
        for (Position p : game.getWallsPosition()){
            im.getGraphics().drawImage(this.wallSprite,p.getX()*5 ,  p.getY()*5, 20,20, null);
        }
    }
}
