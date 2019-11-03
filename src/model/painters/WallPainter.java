package model.painters;

import model.Labyrinthe;
import model.Position;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WallPainter implements Painter {

    private BufferedImage wallSprite;

    public WallPainter(){
        try {
            wallSprite = ImageIO.read(new File("Ressources/wall.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(BufferedImage im, Labyrinthe game) {
        for (Position p : game.getWalls().getWallsPosition()){
            im.getGraphics().drawImage(this.wallSprite,p.getX()*10 ,  (p.getY()+2)*5+5, 20,20, null);
        }
    }
}
