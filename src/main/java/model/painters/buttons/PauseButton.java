package model.painters.buttons;

import util.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PauseButton implements Button{

    BufferedImage play;
    BufferedImage resume;
    private boolean buttonPLay;

    public PauseButton() {
        try {
            play = ImageIO.read(getClass().getClassLoader().getResourceAsStream("pause.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            resume = ImageIO.read(getClass().getClassLoader().getResourceAsStream("resume.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        buttonPLay = true;
    }

    public void drawPlay() {
        this.buttonPLay = true;
    }

    public void drawResume() {
        this.buttonPLay = false;
    }

    public void paint(Graphics g) {
        if (buttonPLay) {
            g.drawImage(play, (int) Constants.rect_pause.getX(), (int)Constants.rect_pause.getY(), (int)Constants.rect_pause.getWidth(), (int)Constants.rect_pause.getHeight(), null);
        }
        else {
            g.drawImage(resume, (int) Constants.rect_pause.getX(), (int)Constants.rect_pause.getY(), (int)Constants.rect_pause.getWidth(), (int)Constants.rect_pause.getHeight(), null);
        }

    }

}
