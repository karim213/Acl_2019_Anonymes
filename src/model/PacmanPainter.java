package model;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import engine.Cmd;
import engine.GamePainter;
import model.painters.ChestPainter;
import model.painters.EnemiesPainter;
import model.painters.HeroPainter;
import model.painters.WallPainter;

import javax.imageio.ImageIO;

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

	private BufferedImage backgroundSprite;

	private WallPainter wallPainter;
	private ChestPainter chestPainter;
	private EnemiesPainter enemiesPainter;
	private HeroPainter heroPainter;

	/**
	 * appelle constructeur parent
	 *
	 * @param labyrinthe
	 *            le jeutest a afficher
	 */
	public PacmanPainter(Labyrinthe labyrinthe) {
		this.WIDTH = labyrinthe.getWIDTH()*5;
		this.HEIGHT = labyrinthe.getHEIGHT()*5;
		this.labyrinthe = labyrinthe;

		try {
			this.backgroundSprite = ImageIO.read(this.getClass().getResourceAsStream("/Ressources/background1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		heroPainter = new HeroPainter();
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
		this.enemiesPainter.draw(im,labyrinthe);
		this.heroPainter.draw(im, labyrinthe);
	}

	@Override
	public int getWidth() {
		return WIDTH;
	}

	@Override
	public int getHeight() {
		return HEIGHT;
	}

	public void drawOver(BufferedImage im) {
		try {
			im.getGraphics().drawImage(ImageIO.read(this.getClass().getResourceAsStream("/Ressources/over.png")), 0, 0, WIDTH, HEIGHT, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void drawWin(BufferedImage im) {
		try {
			im.getGraphics().drawImage(ImageIO.read(this.getClass().getResourceAsStream("/Ressources/w.png")), 0, 0, WIDTH, HEIGHT, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Labyrinthe getLabyrinthe(){
		return this.labyrinthe;
	}
	}
