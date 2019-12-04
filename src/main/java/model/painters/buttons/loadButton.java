package model.painters.buttons;

import util.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class loadButton implements Button {

    private BufferedImage load;

    public loadButton() {
        try {
            load = ImageIO.read(getClass().getClassLoader().getResourceAsStream("load.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void paint(Graphics g) {
        g.drawImage(load, (int) Constants.rect_load.getX(), (int)Constants.rect_load.getY(), (int)Constants.rect_load.getWidth(), (int)Constants.rect_load.getHeight(), null);
    }

}
