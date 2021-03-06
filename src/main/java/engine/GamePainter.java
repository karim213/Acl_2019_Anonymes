package engine;

import model.Labyrinthe;

import java.awt.image.BufferedImage;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 * 
 * represente la maniere de dessiner sur un JPanel
 * 
 */
public interface GamePainter {


	/**
	 * methode dessiner a completer. Elle construit une image correspondant au
	 * jeu. Game est un attribut de l'afficheur
	 * 
	 * @param image
	 *            image sur laquelle dessiner
	 */
	public abstract void draw(BufferedImage image);

	void drawMenu(BufferedImage im);

    void drawSplash(BufferedImage im);

    public abstract void drawOver(BufferedImage image);

	public abstract void drawWin(BufferedImage image);

	public abstract int getWidth();

	public abstract int getHeight();

	public abstract Labyrinthe getLabyrinthe();

	public void drawScore(BufferedImage im);
}
