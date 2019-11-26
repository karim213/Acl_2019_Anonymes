package model.painters;

import model.Labyrinthe;
import model.painters.buttons.PauseButton;
import util.Constants;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ScorePainter implements Painter {

    private BufferedImage heroSprite;
    private BufferedImage pvSprite;
    private BufferedImage background;
    private PauseButton pauseButton = new PauseButton();
    private int width;

    public ScorePainter(){
        try {
            pvSprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream("heal.png"));
            background = ImageIO.read(getClass().getClassLoader().getResourceAsStream("background_score.jpg"));
            heroSprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream("zelda.png"));
        } catch (IOException e) {
        e.printStackTrace();
    }
    }

    @Override
    public void draw(BufferedImage im, Labyrinthe game) {
        im.getGraphics().drawImage(background, 0, 0, Constants.WIDTH * 20, 80, null);

        im.getGraphics().drawImage(heroSprite, 20, 5, 60 , 60, null);


         int decal = 50;
        for (int i = 1 ; i<= game.getHero().getPv(); i++){
            if (i != 1)
                decal = 50;
            im.getGraphics().drawImage(this.pvSprite,decal + i*40 ,  20, 40,40, null);
        }
        if(game.isFinished() == 0)
            pauseButton.paint(im.getGraphics() , "pause");
        else if(game.isFinished() == 1)
            pauseButton.paint(im.getGraphics() , "resume");
    }
}
