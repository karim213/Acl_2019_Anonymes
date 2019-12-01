import engine.GameEngineGraphical;
import factories.TestFactory;

/**
 * lancement du moteur avec le jeu
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {
		GameEngineGraphical engine = TestFactory.createSimpleGame();
		engine.run();
	}

}
