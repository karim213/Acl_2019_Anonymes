package model.painters;

import model.Labyrinthe;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ChestPainter implements Painter {
    @Override
    public void draw(BufferedImage im, Labyrinthe game) {
        Graphics2D crayon = (Graphics2D) im.getGraphics();
        crayon.setColor(Color.yellow);
        crayon.fillOval(game.getChest().getPosition().getX()*10,game.getChest().getPosition().getY()*5,25,25);
    }
}
