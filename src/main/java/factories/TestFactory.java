package factories;

import engine.GameEngineGraphical;
import model.*;

public class TestFactory {
    private static GameEngineGraphical engine = null;

    public static GameEngineGraphical getInstance() {

        if (engine == null) {

            Labyrinthe labyrinthe = new Labyrinthe();
            PacmanPainter painter = new PacmanPainter(labyrinthe);
            PacmanController controller = new PacmanController(labyrinthe);

            engine = new GameEngineGraphical(labyrinthe, painter, controller);
        }

        return engine;
    }
}
