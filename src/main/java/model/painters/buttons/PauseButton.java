package model.painters.buttons;

import util.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PauseButton {

    public void paint(Graphics g , String name) {
        BufferedImage play = null;
        try {
            play = ImageIO.read(getClass().getClassLoader().getResourceAsStream(name+".png"));
            g.drawImage(play, (int) Constants.rect_pause.getX(), (int)Constants.rect_pause.getY(), (int)Constants.rect_pause.getWidth(), (int)Constants.rect_pause.getHeight(), null);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
