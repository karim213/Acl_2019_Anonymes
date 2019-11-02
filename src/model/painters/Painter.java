package model.painters;

import model.Labyrinthe;

import java.awt.image.BufferedImage;

public interface Painter {

    public void draw(BufferedImage im, Labyrinthe game);
}
