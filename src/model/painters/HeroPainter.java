package model.painters;

import engine.Cmd;
import model.Hero;
import model.Labyrinthe;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static util.Constants.HERO_SPRITE;

public class HeroPainter implements Painter {
    private BufferedImage heroSprite;
    private BufferedImage reverseHeroSprite;
    private BufferedImage subHeroSprite;
    private Hero hero;
    private int xoffset;
    private int yoffset;
    private int lastXoffset;
    private int lastYoffset;
    private int heroSize = 80;
    private boolean reverse = false;

    public HeroPainter(){
        try {
            this.heroSprite = ImageIO.read(this.getClass().getResourceAsStream(HERO_SPRITE));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.reverseHeroSprite = mirror(heroSprite);
        this.subHeroSprite = null;
        this.xoffset = 0;
        this.yoffset = 0;
        this.lastXoffset = 0;
        this.lastYoffset = 0;
    }

    @Override
    public void draw(BufferedImage im, Labyrinthe game) {

        this.hero = game.getHero();

        if (hero.isAttaque()){
            if (yoffset + 3 < 6){
                yoffset += 3;
                xoffset = 0;
            }

            if (xoffset >= 7) {
                xoffset = 0;
                yoffset = lastYoffset;
                hero.setAttaque(false);
            }
            xoffset++;
        }
        else {
            yoffset = 0;

            if (this.hero.getCurrentCmd() == Cmd.UP) {
                yoffset += 2;
                xoffset++;
            }
            else if (this.hero.getCurrentCmd() == Cmd.DOWN) {
                yoffset += 0;
                xoffset++;
            }
            else if (this.hero.getCurrentCmd() == Cmd.LEFT) {
                yoffset += 1;
                xoffset++;
                reverse = true;
            }
            else if(this.hero.getCurrentCmd() == Cmd.RIGHT){
                yoffset += 1;
                xoffset++;
                reverse = false;
            }
            else {
                xoffset = lastXoffset;
                yoffset = lastYoffset;
            }

            if (xoffset >= 9) {
                xoffset = 0;
            }

            lastXoffset = xoffset;
            lastYoffset = yoffset;
        }

        if (reverse){
            subHeroSprite = reverseHeroSprite.getSubimage(heroSize * xoffset, heroSize * yoffset, heroSize, heroSize);
        }
        else {
            subHeroSprite = heroSprite.getSubimage(heroSize * xoffset, heroSize * yoffset, heroSize, heroSize);
        }

        im.getGraphics().drawImage(subHeroSprite, this.hero.getX()*5, this.hero.getY()*5, 40 , 40   , null);
    }

    public BufferedImage mirror(BufferedImage image){
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-image.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx,
                AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        image = op.filter(image, null);
        return image;
    }
}
