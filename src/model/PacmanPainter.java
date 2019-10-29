package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import engine.Game;
import engine.GamePainter;

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
	 * @param game
	 *            le jeutest a afficher
	 */
	public PacmanPainter(Labyrinthe labyrinthe) {
		this.WIDTH = labyrinthe.getWIDTH()*10;
		this.HEIGHT = labyrinthe.getHEIGHT()*10;
		this.labyrinthe = labyrinthe;
	}

	/**
	 * methode  redefinie de Afficheur retourne une image du jeu
	 */
	@Override
	public void draw(BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		crayon.setColor(Color.blue);
		crayon.fillOval(this.labyrinthe.getHero().getX()*10,this.labyrinthe.getHero().getY()*10,10,10);
	}

	@Override
	public int getWidth() {
		return WIDTH;
	}

	@Override
	public int getHeight() {
		return HEIGHT;
	}

}
