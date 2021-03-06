package model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

import static util.Constants.*;
import engine.GamePainter;
import model.objects.Teleporter;
import model.painters.*;

import javax.imageio.ImageIO;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * afficheur graphique pour le game
 *
 */
public class PacmanPainter implements GamePainter, Serializable {

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
	private ScorePainter scorePainter;
	private HealPainter healPainter;
	private TeleporterPainter teleporterPainter;
	private MenuHomePainter menuHomePainter;
	private SplashScreenPainter splashScreenPainter;
	private SandPainter sandPainter;
	private WaterPainter waterPainter;

	/**
	 * appelle constructeur parent
	 *
	 * @param labyrinthe
	 *            le jeutest a afficher
	 */
	public PacmanPainter(Labyrinthe labyrinthe) {
		this.labyrinthe = labyrinthe;

		try {
			this.backgroundSprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(BG_SPRITE));
		} catch (IOException e) {
			e.printStackTrace();
		}

		wallPainter = new WallPainter();
		sandPainter = new SandPainter();
		waterPainter = new WaterPainter();
		chestPainter = new ChestPainter();
		enemiesPainter = new EnemiesPainter();
		trapPainter = new TrapPainter();
		scorePainter = new ScorePainter();
		healPainter = new HealPainter();
		teleporterPainter = new TeleporterPainter();
		menuHomePainter = new MenuHomePainter();
		heroPainter = new HeroPainter();
		splashScreenPainter = new SplashScreenPainter();

	}

	/**
	 * methode  redefinie de Afficheur retourne une image du jeu
	 */
	@Override
	public void draw(BufferedImage im) {
		im.getGraphics().drawImage(this.backgroundSprite, 0, 0, WIDTH_INTERFACE, HEIGHT_INTERFACE, null);
		this.sandPainter.draw(im, labyrinthe);
		this.waterPainter.draw(im,labyrinthe);
		this.wallPainter.draw(im,labyrinthe);
		this.trapPainter.draw(im,labyrinthe);
		this.chestPainter.draw(im,labyrinthe);
		this.enemiesPainter.draw(im,labyrinthe);
		this.healPainter.draw(im,labyrinthe);
		this.teleporterPainter.draw(im,labyrinthe);

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


	@Override
	public void drawScore(BufferedImage im) {
		this.scorePainter.draw(im,labyrinthe);
	}

	@Override
	public void drawMenu(BufferedImage im) {
		this.menuHomePainter.draw(im,labyrinthe);
	}

	@Override
	public void drawSplash(BufferedImage im) {
		this.splashScreenPainter.draw(im,labyrinthe);
	}



	public void drawOver(BufferedImage im) {
		try {
			im.getGraphics().drawImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream(GAME_OVER_SPRITE)), 0, 0, WIDTH_INTERFACE, HEIGHT_INTERFACE, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void drawWin(BufferedImage im) {
		try {
			im.getGraphics().drawImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream(WIN_SPRITE)), 0, 0, WIDTH_INTERFACE, HEIGHT_INTERFACE, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Labyrinthe getLabyrinthe(){
		return this.labyrinthe;
	}
}
