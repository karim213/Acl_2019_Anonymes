package factories;

import engine.GameEngineGraphical;
import model.*;
import model.enemies.Enemies;
import model.enemies.EnemyMovementStrategy;
import model.enemies.Monster;
import model.enemies.RandomMovement;
import model.objects.Chest;
import model.walls.Walls;

public class TestFactory {
    private static GameEngineGraphical engine = null;

    public static GameEngineGraphical getInstance() {

        if (engine == null) {



            Walls walls = new Walls();
            walls.addWall(0, 40);
            walls.addWall(22, 20);
            walls.addWall(30, 30);
            walls.addWall(40, 40);

            Labyrinthe labyrinthe = new Labyrinthe(40 , 40, new Hero(20, 10), walls, new Chest(new Position(20, 35)));

            Enemies enemies = new Enemies();
            EnemyMovementStrategy strategy = new RandomMovement();
            enemies.addEnemie(new Monster(30, 39,labyrinthe));
            enemies.addEnemie(new Monster(35, 30,labyrinthe));
            enemies.addEnemie(new Monster(10, 10,labyrinthe));
            enemies.addEnemie(new Monster(9, 25,labyrinthe));
            enemies.setStrategy(strategy);
            labyrinthe.setEnemies(enemies);

            PacmanPainter painter = new PacmanPainter(labyrinthe);
            PacmanController controller = new PacmanController(labyrinthe);

            engine = new GameEngineGraphical(labyrinthe, painter, controller);
        }

        return engine;
    }
}
