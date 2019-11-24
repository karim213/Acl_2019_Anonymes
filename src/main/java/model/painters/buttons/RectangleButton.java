package model.painters.buttons;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class RectangleButton extends JPanel {

    Rectangle2D.Double rect;
    public RectangleButton() {

        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Point point = e.getPoint();
                System.out.println(checkRectArea(point));
                // Do whatever else you want here.
            }
        });
    }

    public boolean checkRectArea(Point point) {
        return rect.contains(point);
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        rect = new Rectangle2D.Double(10, 10, 150, 75);
        g2.draw(rect);
        BufferedImage play = null;
       /* try {
            play = ImageIO.read(getClass().getClassLoader().getResourceAsStream("play.png"));
            g.drawImage(play, (int)rect.getX(), (int)rect.getY(), (int)rect.getWidth(), (int)rect.getHeight(), null);

        } catch (IOException e) {
            e.printStackTrace();
        }*/


    }

}
