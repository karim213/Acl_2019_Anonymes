import engine.Game;
import factories.TestFactory;
import model.*;
import engine.GameEngineGraphical;
import model.enemies.Enemies;
import model.enemies.Monster;
import model.enemies.RandomMovement;
import model.objects.Chest;
import model.walls.Walls;

/**
 * lancement du moteur avec le jeu
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {

		GameEngineGraphical engine = TestFactory.getInstance();
		engine.run();
	}

}
