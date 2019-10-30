package factories;

import engine.GameEngineGraphical;
import model.*;
import model.enemies.Enemies;
import model.enemies.Monster;
import model.enemies.RandomMovement;
import model.objects.Chest;
import model.walls.Walls;

public class TestFactory {
    private static GameEngineGraphical engine = null;

    public static GameEngineGraphical getInstance() {

        if (engine == null) {

            Enemies enemies = new Enemies();
            RandomMovement startegie = new RandomMovement();
            enemies.addEnemie(new Monster(30, 60, enemies, startegie));
            enemies.addEnemie(new Monster(40, 50, enemies, startegie));
            enemies.addEnemie(new Monster(50, 40, enemies, startegie));
            enemies.addEnemie(new Monster(60, 30, enemies, startegie));

            Walls walls = new Walls();
            walls.addWall(20, 20);
            walls.addWall(30, 30);
            walls.addWall(40, 40);
            walls.addWall(55, 55);

            Labyrinthe labyrinthe = new Labyrinthe(80 , 80, new Hero(40, 40), enemies, walls, new Chest(new Position(20, 35)));

            startegie.setGame(labyrinthe);

            PacmanPainter painter = new PacmanPainter(labyrinthe);
            PacmanController controller = new PacmanController(labyrinthe);

            engine = new GameEngineGraphical(labyrinthe, painter, controller);
        }

        return engine;
    }
}
