package model;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import engine.Cmd;
import engine.Game;
import engine.GameController;
import util.Constants;


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
	public void mouseClicked(MouseEvent e) {
		if(labyrinthe.isFinished() == -1) {
			if (Constants.rect_play.contains(e.getPoint())) {
				labyrinthe.setisFinished(0);
			} else if (Constants.rect_load.contains(e.getPoint())) {
				System.out.println("load");
			} else if (Constants.rect_quit.contains(e.getPoint())) {
				System.exit(0);
			}
		}else if(labyrinthe.isFinished() == 0){
			if (Constants.rect_pause.contains(e.getPoint())) {
				System.out.println("yes");
				if (labyrinthe.isFinished() == 0) {
					labyrinthe.setisFinished(1);
					System.out.println("pause");
				}else if(labyrinthe.isFinished() == 1) {
					labyrinthe.setisFinished(0);
					System.out.println("resume");
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	/**
	 * met a jour les commandes en fonctions des touches appuyees
	 */
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyChar()) {
		// si on appuie sur 'q',commande joueur est gauche
			case 'q':
			case 'Q':
				this.commandeEnCours = Cmd.LEFT;
				break;

			case 'd':
			case 'D':
				this.commandeEnCours = Cmd.RIGHT;
				break;

			case 'z':
			case 'Z':
				this.commandeEnCours = Cmd.UP;
				break;

			case 's':
			case 'S':
				this.commandeEnCours = Cmd.DOWN;
				break;

			case 't' :
			case 'T':
                 this.commandeEnCours = Cmd.ATTACK;
                 break;
			default:
				this.commandeEnCours = Cmd.IDLE;
				break;
		}

	}

	@Override
	/**
	 * met a jour les commandes quand le joueur relache une touche
	 */
	public void keyReleased(KeyEvent e) {
		this.commandeEnCours = Cmd.IDLE;
	}

	@Override
	/**
	 * ne fait rien
	 */
	public void keyTyped(KeyEvent e) {

	}

    public Labyrinthe getLabyrinthe(){
		return this.labyrinthe;
	}


}
