package model;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import engine.Cmd;
import engine.Game;
import engine.GamePainter;

import javax.imageio.ImageIO;
import javax.swing.border.EmptyBorder;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * afficheur graphique pour le game
 * 
 */
public class PacmanPainter implements GamePainter {

	/**
	 * la taille des cases
	 */
	protected static int WIDTH ;
	protected static int HEIGHT;
	private Labyrinthe labyrinthe;

	/**
	 * appelle constructeur parent
	 * 
	 * @param labyrinthe
	 *            le jeutest a afficher
	 */
	public PacmanPainter(Labyrinthe labyrinthe) {
		this.WIDTH = labyrinthe.getWIDTH()*10+20;
		this.HEIGHT = labyrinthe.getHEIGHT()*5+40;
		this.labyrinthe = labyrinthe;
	}

	/**
	 * methode  redefinie de Afficheur retourne une image du jeu
	 */
	@Override
	public void draw(BufferedImage im) {
		//Graphics2D crayon = (Graphics2D) im.getGraphics();
		//crayon.setColor(Color.blue);
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("heroSprite.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		final float FACTOR  = 50f;
		int scaleX = 50;
		int scaleY = 50;
		Image image = img.getScaledInstance(scaleX, scaleY, Image.SCALE_SMOOTH);

		//BufferedImage buffered = new BufferedImage(scaleX, scaleY, TYPE);


        if(this.labyrinthe.getHero().getCurrentCmd() == Cmd.RIGHT){
			img = img.getSubimage(
					180 + 75 * (this.labyrinthe.getHero().getX()%4),
					110,
					56,
					120
			);
		}else if(this.labyrinthe.getHero().getCurrentCmd() == Cmd.LEFT){
			img = img.getSubimage(
					180 + 75 * (this.labyrinthe.getHero().getX()%4),
					110,
					56,
					120
			);
			img = mirror(img);
		}else if(this.labyrinthe.getHero().getCurrentCmd() == Cmd.UP){
			img = img.getSubimage(
					180 + 70 * (this.labyrinthe.getHero().getY()%10),
					235,
					56,
					120
			);

		}else if(this.labyrinthe.getHero().getCurrentCmd() == Cmd.DOWN){
			img = img.getSubimage(
					180 + 70 * (this.labyrinthe.getHero().getY()%10),
					0,
					56,
					120
			);
		}else if(this.labyrinthe.getHero().getCurrentCmd() == Cmd.IDLEUP){
			img = img.getSubimage(
					180,
					110,
					56,
					120
			);

		}else if(this.labyrinthe.getHero().getCurrentCmd() == Cmd.IDLERIGHT){
			img = img.getSubimage(
					180,
					110,
					56,
					120
			);
		}else if(this.labyrinthe.getHero().getCurrentCmd() == Cmd.IDLELEFT){
			img = img.getSubimage(
					180,
					110,
					56,
					120
			);
			img = mirror(img);
		}else {
			img = img.getSubimage(
					180,
					0,
					56,
					120
			);
		}

		try {
			im.getGraphics().drawImage(ImageIO.read(new File("background.png")), 0, 0, WIDTH, HEIGHT, null);
		} catch (IOException e) {
			e.printStackTrace();
		}

		im.getGraphics().drawImage(img, this.labyrinthe.getHero().getX()*10, this.labyrinthe.getHero().getY()*5, 20 , 40   , null);

		//crayon.fillOval(this.labyrinthe.getHero().getX()*10,this.labyrinthe.getHero().getY()*10,10,10);
}

	@Override
	public int getWidth() {
		return WIDTH;
	}

	@Override
	public int getHeight() {
		return HEIGHT;
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
