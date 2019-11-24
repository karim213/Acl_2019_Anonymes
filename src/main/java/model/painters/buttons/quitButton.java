package model.painters.buttons;

import util.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class quitButton  {

    public void paint(Graphics g) {
        BufferedImage quit = null;
        try {
            quit = ImageIO.read(getClass().getClassLoader().getResourceAsStream("quit.png"));
            g.drawImage(quit, (int) Constants.rect_quit.getX(), (int)Constants.rect_quit.getY(), (int)Constants.rect_quit.getWidth(), (int)Constants.rect_quit.getHeight(), null);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
