package model.painters;

import model.Labyrinthe;
import model.Position;


import java.awt.*;
import java.awt.image.BufferedImage;

public class EnemiesPainter implements Painter {
    @Override
    public void draw(BufferedImage im, Labyrinthe game) {
        Graphics2D crayon = (Graphics2D) im.getGraphics();
        crayon.setColor(Color.red);

        for (Position p : game.getEnemies().getEnemiesPosition()){
            crayon.fillRect(p.getX()*10,p.getY()*5,10,10);
        }
    }
}
