package engine;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 */

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawingScorePanel extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * la clase chargee de Dessiner
	 */
	private GamePainter painter;

	/**
	 * image suivante est l'image cachee sur laquelle dessiner
	 */
	private BufferedImage nextImage;

	/**
	 * image en cours est l'image entrain d'etre affichee
	 */
	private BufferedImage currentImage;

	/**
	 * la taille des images
	 */
	private int width, height;


	public DrawingScorePanel(GamePainter painter) {
		super();
		this.width = painter.getWidth();
		this.height = 80;
		this.painter = painter;
		this.setPreferredSize(new Dimension(this.width, this.height));

		// cree l'image buffer et son graphics
		this.nextImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		this.currentImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

	}

	/**
	 * demande de mettre a jour le rendu de l'image sur le Panel. Creer une
	 * nouvelle image vide sur laquelle dessiner
	 */
	public void drawGame(boolean over, String s) {
		this.painter.drawScore(this.nextImage);

		BufferedImage temp = this.currentImage;
		this.currentImage = this.nextImage;
		this.nextImage = temp;
		this.nextImage.getGraphics()
				.fillRect(0, 0, this.width, this.height);
		this.repaint();
	}

	/**
	 * redefinit la methode paint consiste a dessiner l'image en cours
	 *
	 * @param g
	 *            graphics pour dessiner
	 */
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(this.currentImage, 0, 0, getWidth(), getHeight(), 0, 0,
				getWidth(), getHeight(), null);
	}

}
