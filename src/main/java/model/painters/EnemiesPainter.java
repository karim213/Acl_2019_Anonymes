package model.painters;

import engine.Cmd;
import model.Labyrinthe;
import model.enemies.Enemy;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static util.Constants.GHOST_SPRITE;
import static util.Constants.SNAKE_SPRITE;

public class EnemiesPainter implements Painter {

    private BufferedImage imgGhost;
    private BufferedImage mirrorImgGhost;
    private BufferedImage imgEnemies;
    private List<BufferedImage> spritesUp ;
    private List<BufferedImage> spritesDown ;
    private List<BufferedImage> spritesRight ;
    private List<BufferedImage> spritesLeft ;
    private int numberSprite;

    public EnemiesPainter(){
        try {
            imgGhost = ImageIO.read(getClass().getClassLoader().getResourceAsStream(GHOST_SPRITE));
        } catch (IOException e) {
            e.printStackTrace();
        }

        mirrorImgGhost = mirror(imgGhost);

        numberSprite = 0;
        try {
            imgEnemies = ImageIO.read(getClass().getClassLoader().getResourceAsStream(SNAKE_SPRITE));
        } catch (IOException e) {
            e.printStackTrace();
        }

        spritesUp = new ArrayList<>();
        for(int i = 0; i<4;i++){
            spritesUp.add(imgEnemies.getSubimage(56*i,0,56,64));
        }
        spritesDown = new ArrayList<>();
        spritesDown.add(imgEnemies.getSubimage(224,0,68,64));
        spritesDown.add(imgEnemies.getSubimage(292,0,60,64));
        spritesDown.add(imgEnemies.getSubimage(352,0,64,64));
        spritesDown.add(imgEnemies.getSubimage(416,0,58,64));

        spritesRight = new ArrayList<>();
        spritesRight.add(imgEnemies.getSubimage(474,0,94,64));
        spritesRight.add(imgEnemies.getSubimage(568,0,84,64));
        spritesRight.add(imgEnemies.getSubimage(652,0,88,64));
        spritesRight.add(imgEnemies.getSubimage(740,0,60,64));

        spritesLeft = new ArrayList<>();
        for(BufferedImage image1 :spritesRight){
            spritesLeft.add(mirror(image1));
        }
    }


    public BufferedImage mirror(BufferedImage image){
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-image.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx,
                AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        image = op.filter(image, null);
        return image;
    }



    @Override
    public void draw(BufferedImage im, Labyrinthe game) {
        for(Enemy enemy : game.getEnemies().getEnemies()){

            if (enemy.getType().equals("Monster")) {
                // HitBox
                // im.getGraphics().drawRect(enemy.getX() *5 , enemy.getY()*5, 20, 20);

                if(enemy.getCurrentCmd() == Cmd.UP){
                    im.getGraphics().drawImage(spritesUp.get(numberSprite), enemy.getX()*5, enemy.getY()*5, 20 , 20   , null);
                }
                else if(enemy.getCurrentCmd() == Cmd.DOWN){
                    im.getGraphics().drawImage(spritesDown.get(numberSprite), enemy.getX()*5, enemy.getY()*5, 20 , 20   , null);
                }
                else if(enemy.getCurrentCmd() == Cmd.LEFT){
                    im.getGraphics().drawImage(spritesRight.get(numberSprite), enemy.getX()*5, enemy.getY()*5, 20 , 20   , null);
                }
                else if(enemy.getCurrentCmd() == Cmd.RIGHT){
                    im.getGraphics().drawImage(spritesLeft.get(numberSprite), enemy.getX()*5, enemy.getY()*5, 20 , 20   , null);
                }
            }
            else {
                // HitBox
                // im.getGraphics().drawRect(enemy.getX() *5 , enemy.getY()*5, 40, 40);

                if(enemy.getCurrentCmd() == Cmd.LEFT || enemy.getCurrentCmd() == Cmd.DOWN){
                    im.getGraphics().drawImage(mirrorImgGhost, enemy.getX()*5, enemy.getY()*5, 40 , 40   , null);
                }
                else {
                    im.getGraphics().drawImage(imgGhost, enemy.getX()*5, enemy.getY()*5, 40 , 40   , null);
                }
            }
        }
        numberSprite++;
        if (numberSprite >= 4) {
            numberSprite = 0;
        }
    }
}
