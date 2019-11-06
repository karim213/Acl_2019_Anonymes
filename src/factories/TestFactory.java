package factories;

import engine.GameEngineGraphical;
import model.*;
import model.enemies.Enemies;
import model.enemies.EnemyMovementStrategy;
import model.enemies.Monster;
import model.enemies.RandomMovement;
import model.objects.Chest;
import model.objects.Objects;
import model.walls.Walls;

public class TestFactory {
    private static GameEngineGraphical engine = null;

    public static GameEngineGraphical getInstance() {

        if (engine == null) {



            Walls walls = new Walls();
            walls.addWall(20, 20);
            walls.addWall(24, 20);
            walls.addWall(28, 20);
            walls.addWall(32, 20);
            walls.addWall(36, 20);
            walls.addWall(40, 20);
            walls.addWall(44, 20);
            walls.addWall(48, 20);


            walls.addWall(20, 24);
            walls.addWall(24, 24);

            walls.addWall(20, 24);
            walls.addWall(48, 24);

            walls.addWall(20, 28);
            walls.addWall(48, 28);

            walls.addWall(20, 32);
            walls.addWall(48, 32);

            walls.addWall(20, 34);
            walls.addWall(48, 34);

            walls.addWall(20, 34);
            walls.addWall(48, 34);

            walls.addWall(20, 38);
            walls.addWall(48, 38);

            walls.addWall(20, 42);
            walls.addWall(48, 42);


            walls.addWall(20, 46);
            walls.addWall(48, 46);


            walls.addWall(20, 48);
            walls.addWall(48, 48);


            walls.addWall(20, 52);
            walls.addWall(24, 52);
            walls.addWall(28, 52);
            walls.addWall(32, 52);
            walls.addWall(36, 52);
            walls.addWall(40, 52);
            walls.addWall(44, 52);

            Objects objects= new Objects();
            objects.addChest(new Position(20,5));
            objects.addTrap(new Position(30 , 5));
            objects.addTrap(new Position(60 , 5));

            Labyrinthe labyrinthe = new Labyrinthe(new Hero(70, 50), walls,objects);

            Enemies enemies = new Enemies();
            EnemyMovementStrategy strategy = new RandomMovement();
            enemies.addEnemie(new Monster(28, 44,labyrinthe));
            enemies.addEnemie(new Monster(31, 42,labyrinthe));
            enemies.addEnemie(new Monster(26, 29,labyrinthe));
            enemies.addEnemie(new Monster(30, 30,labyrinthe));


            enemies.addEnemie(new Monster(28, 60,labyrinthe));
            enemies.addEnemie(new Monster(38, 36,labyrinthe));
            enemies.addEnemie(new Monster(42, 41,labyrinthe));
            enemies.addEnemie(new Monster(60, 60,labyrinthe));


            enemies.addEnemie(new Monster(10, 60,labyrinthe));
            enemies.addEnemie(new Monster(63, 10,labyrinthe));
            enemies.addEnemie(new Monster(26, 12,labyrinthe));
            enemies.addEnemie(new Monster(39, 38,labyrinthe));



            enemies.setStrategy(strategy);
            labyrinthe.setEnemies(enemies);

            PacmanPainter painter = new PacmanPainter(labyrinthe);
            PacmanController controller = new PacmanController(labyrinthe);

            engine = new GameEngineGraphical(labyrinthe, painter, controller);
        }

        return engine;
    }
}
