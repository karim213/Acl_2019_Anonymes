package model.painters;

import model.Labyrinthe;
import model.painters.buttons.*;
import util.Constants;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MenuHomePainter implements Painter {

    private PlayButton playButton;
    private loadButton loadButton;
    private quitButton quitButton;
    private RectangleButton rectangleButton;
    public MenuHomePainter(){
      playButton = new PlayButton();
      loadButton = new loadButton();
      quitButton = new quitButton();

    }

    @Override
    public void draw(BufferedImage im, Labyrinthe game) {
        try {
            BufferedImage background = ImageIO.read(getClass().getClassLoader().getResourceAsStream("background_menu.jpg"));
            im.getGraphics().drawImage(background, 0 , 0 , im.getWidth() , im.getHeight(), null);

            BufferedImage personnage = ImageIO.read(getClass().getClassLoader().getResourceAsStream("personnage.png"));
            im.getGraphics().drawImage(personnage, 500 , 80 , 250 , 320, null);

            BufferedImage logo = ImageIO.read(getClass().getClassLoader().getResourceAsStream("logo.png"));
            im.getGraphics().drawImage(logo, im.getWidth()/2 - 170 , 20 , 340 , 80, null);

        } catch (IOException e) {
            e.printStackTrace();
        }

        playButton.paint(im.getGraphics());
        loadButton.paint(im.getGraphics());
        quitButton.paint(im.getGraphics());
        //rectangleButton.draw(im.getGraphics());
    }
}
