package engine;

import model.Hero;

import java.util.List;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 *         un jeu qui peut evoluer (avant de se terminer) sur un plateau width x
 *         height
 */
public interface Game {

	/**
	 * methode qui contient l'evolution du jeu en fonction de la commande
	 * 
	 * @param userCmd
	 *            commande utilisateur
	 */
	public void evolve(Cmd userCmd);

	/**
	 * @return true si et seulement si le jeu est fini
	 */
	public int isFinished();

	public void setisFinished(int status);


	/**
	 * @return true si et seulement si le joueur gagne
	 */
	public boolean isOver();

	public Hero getHero();

	public void setLabyrinthe(String mode, List<String> save);

}
