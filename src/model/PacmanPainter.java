package model;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

import engine.Cmd;
import engine.Game;
import engine.GamePainter;
import model.painters.ChestPainter;
import model.painters.EnemiesPainter;
import model.painters.WallPainter;

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
	protected Labyrinthe labyrinthe;

	private BufferedImage heroSprite;
	private BufferedImage subHeroSprite;
	private BufferedImage backgroundSprite;

	private WallPainter wallPainter;
	private ChestPainter chestPainter;
	private EnemiesPainter enemiesPainter;

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

		try {
			this.heroSprite = ImageIO.read(new File("Ressources/heroSpriteMovements.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.subHeroSprite = null;

		try {
			this.backgroundSprite = ImageIO.read(new File("Ressources/background1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		wallPainter = new WallPainter();
		chestPainter = new ChestPainter();
		enemiesPainter = new EnemiesPainter();

	}

	/**
	 * methode  redefinie de Afficheur retourne une image du jeu
	 */
	@Override
	public void draw(BufferedImage im) {
		im.getGraphics().drawImage(this.backgroundSprite, 0, 0, WIDTH, HEIGHT, null);
		this.wallPainter.draw(im,labyrinthe);
		this.chestPainter.draw(im,labyrinthe);
		this.drawHero(im);
	}

	public void drawHero(BufferedImage im){

		int scaleX;
		int scaleY;
		int nbrEtape;
		int Decal = 0;
		int fact;
		int heroWidth = 56;
		int heroHeight = 120;

		if (this.labyrinthe.getHero().isAttaque()) {
			if (this.labyrinthe.getHero().getCurrentCmd() == Cmd.IDLEUP || this.labyrinthe.getHero().getCurrentCmd() == Cmd.UP) {
				scaleY = 480;
				scaleX = 60;
			} else if (this.labyrinthe.getHero().getCurrentCmd() == Cmd.IDLELEFT || this.labyrinthe.getHero().getCurrentCmd() == Cmd.LEFT) {
				scaleY = 360;
				scaleX = 60;
			} else if (this.labyrinthe.getHero().getCurrentCmd() == Cmd.IDLERIGHT || this.labyrinthe.getHero().getCurrentCmd() == Cmd.RIGHT) {
				scaleY = 360;
				scaleX = 60;
			} else {
				scaleY = 360;
				scaleX = 266;
			}

			heroWidth = 90;
			fact = 0;
			Decal = 0;
			nbrEtape = 1;

			subHeroSprite = heroSprite.getSubimage(
					scaleX + Decal * (fact % nbrEtape),
					scaleY,
					heroWidth,
					heroHeight
			);

			//vue qu'on a pas un prite pour la direction gauche on inverse celui de la direction gache :)
			if (this.labyrinthe.getHero().getCurrentCmd() == Cmd.IDLERIGHT || this.labyrinthe.getHero().getCurrentCmd() == Cmd.RIGHT)
				subHeroSprite = mirror(subHeroSprite);

			this.labyrinthe.getHero().setAttaque(false);

		} else {
			if (this.labyrinthe.getHero().getCurrentCmd() == Cmd.RIGHT || this.labyrinthe.getHero().getCurrentCmd() == Cmd.LEFT) {
				scaleX = 180;
				scaleY = 110;
				nbrEtape = 4;
				Decal = 75;
				fact = this.labyrinthe.getHero().getX();
			} else if (this.labyrinthe.getHero().getCurrentCmd() == Cmd.UP) {
				scaleX = 180;
				scaleY = 235;
				nbrEtape = 10;
				Decal = 70;
				fact = this.labyrinthe.getHero().getY();

			} else if (this.labyrinthe.getHero().getCurrentCmd() == Cmd.DOWN) {
				scaleX = 180;
				scaleY = 0;
				nbrEtape = 10;
				Decal = 70;
				fact = this.labyrinthe.getHero().getY();

			} else if (this.labyrinthe.getHero().getCurrentCmd() == Cmd.IDLEUP) {

				scaleX = 180;
				scaleY = 235;
				nbrEtape = 1;
				Decal = 0;
				fact = this.labyrinthe.getHero().getY();

			} else if (this.labyrinthe.getHero().getCurrentCmd() == Cmd.IDLERIGHT || this.labyrinthe.getHero().getCurrentCmd() == Cmd.IDLELEFT) {

				scaleX = 180;
				scaleY = 110;
				nbrEtape = 1;
				Decal = 0;
				fact = this.labyrinthe.getHero().getY();


			} else {
				scaleX = 180;
				scaleY = 0;
				Decal = 0;
				nbrEtape = 1;
				fact = this.labyrinthe.getHero().getY();

			}

			subHeroSprite = heroSprite.getSubimage(
					scaleX + Decal * (fact % nbrEtape),
					scaleY,
					heroWidth,
					heroHeight
			);

			//vue qu'on a pas un prite pour la direction gauche on inverse celui de la direction gache :)
			if (this.labyrinthe.getHero().getCurrentCmd() == Cmd.IDLELEFT || this.labyrinthe.getHero().getCurrentCmd() == Cmd.LEFT)
				subHeroSprite = mirror(subHeroSprite);

		}


		im.getGraphics().drawImage(subHeroSprite, this.labyrinthe.getHero().getX()*10, this.labyrinthe.getHero().getY()*5, 20 , 40   , null);

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


	@Override
	public Labyrinthe getLabyrinthe(){
		return this.labyrinthe;
	}
	}
