package model.painters.buttons;

import util.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PlayButton{

    public void paint(Graphics g) {
        BufferedImage play = null;
        try {
            play = ImageIO.read(getClass().getClassLoader().getResourceAsStream("play.png"));
            g.drawImage(play, (int) Constants.rect_play.getX(), (int)Constants.rect_play.getY(), (int)Constants.rect_play.getWidth(), (int)Constants.rect_play.getHeight(), null);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
