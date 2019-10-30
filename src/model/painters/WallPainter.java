package model.painters;

import model.Labyrinthe;
import model.Position;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WallPainter implements Painter {
    @Override
    public void draw(BufferedImage im, Labyrinthe game) {
        Graphics2D crayon = (Graphics2D) im.getGraphics();
        crayon.setColor(Color.green);

        for (Position p : game.getWalls().getWallsPosition()){
            crayon.fillRect(p.getX()*10,p.getY()*5,25,25);
        }
    }
}
