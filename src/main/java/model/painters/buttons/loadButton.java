package model.painters.buttons;

import util.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class loadButton   {

    public void paint(Graphics g) {
        BufferedImage load = null;
        try {
            load = ImageIO.read(getClass().getClassLoader().getResourceAsStream("load.png"));
            g.drawImage(load, (int) Constants.rect_load.getX(), (int)Constants.rect_load.getY(), (int)Constants.rect_load.getWidth(), (int)Constants.rect_load.getHeight(), null);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
