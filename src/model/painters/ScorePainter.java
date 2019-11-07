package model.painters;

import model.Labyrinthe;
import model.Position;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ScorePainter implements Painter {

    private BufferedImage heroSprite;
    private BufferedImage pvSprite;
    private BufferedImage background;

    public ScorePainter(){
        try {
            pvSprite = ImageIO.read(this.getClass().getResourceAsStream("/Ressources/heal.png"));
            background = ImageIO.read(this.getClass().getResourceAsStream("/Ressources/background_score.jpg"));
            heroSprite = ImageIO.read(this.getClass().getResourceAsStream("/Ressources/zelda.png"));

        } catch (IOException e) {
        e.printStackTrace();
    }
    }

    @Override
    public void draw(BufferedImage im, Labyrinthe game) {
        im.getGraphics().drawImage(background, 0, 0, game.getWIDTH()*20 , 80, null);

        im.getGraphics().drawImage(heroSprite, 20, 5, 60 , 60, null);

         int decal = 50;
        for (int i = 1 ; i<= game.getHero().getPv(); i++){
            if (i != 1)
                decal = 50;
            im.getGraphics().drawImage(this.pvSprite,decal + i*40 ,  20, 40,40, null);
        }
    }
}
