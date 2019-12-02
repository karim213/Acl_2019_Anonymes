package model.painters.buttons;

import util.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PlayButton implements Button{

    BufferedImage play;

    public PlayButton() {
        try {
            play = ImageIO.read(getClass().getClassLoader().getResourceAsStream("play.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        g.drawImage(play, (int) Constants.rect_play.getX(), (int)Constants.rect_play.getY(), (int)Constants.rect_play.getWidth(), (int)Constants.rect_play.getHeight(), null);
    }

}
