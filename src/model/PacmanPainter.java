package model;

import java.awt.image.BufferedImage;
import java.io.IOException;

import static util.Constants.*;
import engine.GamePainter;
import model.painters.*;

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
	private final int WIDTH_INTERFACE = WIDTH*5;
	private final int HEIGHT_INTERFACE = HEIGHT*5;
	private Labyrinthe labyrinthe;

	private BufferedImage backgroundSprite;

	private WallPainter wallPainter;
	private ChestPainter chestPainter;
	private EnemiesPainter enemiesPainter;
	private HeroPainter heroPainter;
	private TrapPainter trapPainter;

	/**
	 * appelle constructeur parent
	 *
	 * @param labyrinthe
	 *            le jeutest a afficher
	 */
	public PacmanPainter(Labyrinthe labyrinthe) {
		this.labyrinthe = labyrinthe;

		try {
			this.backgroundSprite = ImageIO.read(this.getClass().getResourceAsStream(BG_SPRITE));
		} catch (IOException e) {
			e.printStackTrace();
		}

		heroPainter = new HeroPainter();
		wallPainter = new WallPainter();
		chestPainter = new ChestPainter();
		enemiesPainter = new EnemiesPainter();
		trapPainter = new TrapPainter();


	}

	/**
	 * methode  redefinie de Afficheur retourne une image du jeu
	 */
	@Override
	public void draw(BufferedImage im) {
		im.getGraphics().drawImage(this.backgroundSprite, 0, 0, WIDTH_INTERFACE, HEIGHT_INTERFACE, null);
		this.wallPainter.draw(im,labyrinthe);
		this.trapPainter.draw(im,labyrinthe);
		this.chestPainter.draw(im,labyrinthe);
		this.enemiesPainter.draw(im,labyrinthe);
		this.heroPainter.draw(im,labyrinthe);
	}

	@Override
	public int getWidth() {
		return WIDTH_INTERFACE;
	}

	@Override
	public int getHeight() {
		return HEIGHT_INTERFACE;
	}

	public void drawOver(BufferedImage im) {
		try {
			im.getGraphics().drawImage(ImageIO.read(this.getClass().getResourceAsStream(GAME_OVER_SPRITE)), 0, 0, WIDTH_INTERFACE, HEIGHT_INTERFACE, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void drawWin(BufferedImage im) {
		try {
			im.getGraphics().drawImage(ImageIO.read(this.getClass().getResourceAsStream(WIN_SPRITE)), 0, 0, WIDTH_INTERFACE, HEIGHT_INTERFACE, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Labyrinthe getLabyrinthe(){
		return this.labyrinthe;
	}
	}
