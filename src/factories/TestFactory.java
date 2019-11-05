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
            walls.addWall(20, 20);
            walls.addWall(22, 20);
            walls.addWall(30, 30);
            walls.addWall(40, 40);
            walls.addWall(55, 55);

            Labyrinthe labyrinthe = new Labyrinthe(80 , 80, new Hero(0, 0), walls, new Chest(new Position(20, 35)));

            Enemies enemies = new Enemies();
            EnemyMovementStrategy strategy = new RandomMovement();
            enemies.addEnemie(new Monster(30, 60,labyrinthe));
            enemies.addEnemie(new Monster(40, 50,labyrinthe));
            enemies.addEnemie(new Monster(50, 40,labyrinthe));
            enemies.addEnemie(new Monster(60, 30,labyrinthe));
            enemies.setStrategy(strategy);
            labyrinthe.setEnemies(enemies);

            PacmanPainter painter = new PacmanPainter(labyrinthe);
            PacmanController controller = new PacmanController(labyrinthe);

            engine = new GameEngineGraphical(labyrinthe, painter, controller);
        }

        return engine;
    }
}
