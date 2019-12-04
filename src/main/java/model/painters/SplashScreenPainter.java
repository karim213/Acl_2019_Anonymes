package model.painters;

import model.Labyrinthe;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SplashScreenPainter implements Painter {

    private BufferedImage background;
    private  BufferedImage logo;


    public SplashScreenPainter(){

        try {
            background = ImageIO.read(getClass().getClassLoader().getResourceAsStream("splash.jpg"));
            logo = ImageIO.read(getClass().getClassLoader().getResourceAsStream("anonymes.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void draw(BufferedImage im, Labyrinthe game) {
       im.getGraphics().drawImage(background, 0 , 0 , im.getWidth() , im.getHeight(), null);
       im.getGraphics().drawImage(logo, im.getWidth()/2 - 200 , 150 , 400 , 80, null);
    }
}
