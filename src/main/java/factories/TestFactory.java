package factories;

import engine.GameEngineGraphical;
import model.*;
import model.enemies.*;
import model.objects.*;
import model.walls.Walls;

public class TestFactory {
    private static GameEngineGraphical engine = null;

    public static GameEngineGraphical getInstance() {

        if (engine == null) {

            Labyrinthe labyrinthe = new Labyrinthe(new Hero(4, 4));
            PacmanPainter painter = new PacmanPainter(labyrinthe);
            PacmanController controller = new PacmanController(labyrinthe);

            engine = new GameEngineGraphical(labyrinthe, painter, controller);
        }

        return engine;
    }
}
