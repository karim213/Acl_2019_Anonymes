package model;

import java.awt.event.KeyEvent;

import engine.Cmd;
import engine.Game;
import engine.GameController;


/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * controleur de type KeyListener
 *
 */
public class PacmanController implements GameController {

	/**
	 * commande en cours
	 */
	private Cmd commandeEnCours;
	private Labyrinthe labyrinthe;
	/**
	 * construction du controleur par defaut le controleur n'a pas de commande
	 */
	public PacmanController(Labyrinthe labyrinthe)
	{
		this.commandeEnCours = Cmd.IDLE;
		this.labyrinthe = labyrinthe;
	}

	/**
	 * quand on demande les commandes, le controleur retourne la commande en
	 * cours
	 *
	 * @return commande faite par le joueur
	 */
	public Cmd getCommand() {
		return this.commandeEnCours;
	}

	@Override
	/**
	 * met a jour les commandes en fonctions des touches appuyees
	 */
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyChar()) {
			// si on appuie sur 'q',commande joueur est gauche
			case 'l':
			case 'L':
				this.commandeEnCours = Cmd.LEFT;
				break;

			case 'r':
			case 'R':
				this.commandeEnCours = Cmd.RIGHT;
				break;

			case 'u':
			case 'U':
				this.commandeEnCours = Cmd.UP;
				break;

			case 'd':
			case 'D':
				this.commandeEnCours = Cmd.DOWN;
				break;

			default:
				this.commandeEnCours = Cmd.IDLE;
				break;
		}
		this.labyrinthe.evolve(commandeEnCours);

	}

	@Override
	/**
	 * met a jour les commandes quand le joueur relache une touche
	 */
	public void keyReleased(KeyEvent e) {
		this.labyrinthe.getHero().stop();
	}

	@Override
	/**
	 * ne fait rien
	 */
	public void keyTyped(KeyEvent e) {

	}

}
