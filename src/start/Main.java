package start;

import engine.Game;
import model.Labyrinthe;
import model.PacmanPainter;
import engine.GameEngineGraphical;
import model.PacmanController;
import model.PacmanGame;
import model.enemies.Enemy;
import model.enemies.EnemyMovementStrategy;
import model.enemies.Monster;
import model.enemies.RandomMovement;

/**
 * lancement du moteur avec le jeu
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {

		// creation du jeu particulier et de son afficheur
		//PacmanGame game = new PacmanGame("helpFilePacman.txt");
		Labyrinthe labyrinthe = new Labyrinthe(80 , 80);
		EnemyMovementStrategy strategy = new RandomMovement();
		Enemy monster1 = new Monster(79,79);
		labyrinthe.addEnemy(monster1);
		labyrinthe.setStrategy(strategy);
		PacmanPainter painter = new PacmanPainter(labyrinthe);
		PacmanController controller = new PacmanController(labyrinthe);

		// classe qui lance le moteur de jeu generique
		GameEngineGraphical engine = new GameEngineGraphical(labyrinthe, painter, controller);
		engine.run();
	}

}
