import factories.TestFactory;
import engine.GameEngineGraphical;

/**
 * lancement du moteur avec le jeu
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {
		GameEngineGraphical engine = TestFactory.getInstance();


		engine.run();
	}

}
