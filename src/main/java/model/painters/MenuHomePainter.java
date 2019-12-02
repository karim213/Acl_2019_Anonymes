package model.painters;

import model.Labyrinthe;
import model.painters.buttons.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MenuHomePainter implements Painter {

    private PlayButton playButton;
    private loadButton loadButton;
    private QuitButton quitButton;
    private BufferedImage background;
    private  BufferedImage personnage;
    private  BufferedImage logo;


    public MenuHomePainter(){
      playButton = new PlayButton();
      loadButton = new loadButton();
      quitButton = new QuitButton();

        try {
            background = ImageIO.read(getClass().getClassLoader().getResourceAsStream("background_menu.jpg"));
            personnage = ImageIO.read(getClass().getClassLoader().getResourceAsStream("personnage.png"));
            logo = ImageIO.read(getClass().getClassLoader().getResourceAsStream("logo.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void draw(BufferedImage im, Labyrinthe game) {
       im.getGraphics().drawImage(background, 0 , 0 , im.getWidth() , im.getHeight(), null);
       im.getGraphics().drawImage(personnage, 500 , 110 , 280 , 380, null);
       im.getGraphics().drawImage(logo, im.getWidth()/2 - 170 , 20 , 340 , 80, null);
       playButton.paint(im.getGraphics());
       loadButton.paint(im.getGraphics());
       quitButton.paint(im.getGraphics());
    }
}
