package start;

import engine.Game;
import model.Labyrinthe;
import model.PacmanPainter;
import engine.GameEngineGraphical;
import model.PacmanController;
import model.PacmanGame;

/**
 * lancement du moteur avec le jeu
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {

		// creation du jeu particulier et de son afficheur
		PacmanGame game = new PacmanGame("helpFilePacman.txt");
		Labyrinthe labyrinthe = new Labyrinthe(30 , 30);
		PacmanPainter painter = new PacmanPainter(labyrinthe);
		PacmanController controller = new PacmanController(labyrinthe);

		// classe qui lance le moteur de jeu generique
		GameEngineGraphical engine = new GameEngineGraphical(game, painter, controller);
		engine.run();
	}

}
