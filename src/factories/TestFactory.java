package factories;

import engine.GameEngineGraphical;
import model.*;
import model.enemies.Enemies;
import model.enemies.EnemyMovementStrategy;
import model.enemies.Monster;
import model.enemies.RandomMovement;
import model.objects.Chest;
import model.walls.Walls;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TestFactory {
    private static GameEngineGraphical engine = null;

    public static GameEngineGraphical getInstance() throws IOException  {

        if (engine == null) {

            Walls walls = new Walls();
            walls.addWall(22, 20);
            walls.addWall(24, 20);
            walls.addWall(26, 20);
            walls.addWall(28, 20);
            walls.addWall(30, 20);
            walls.addWall(32, 20);
            walls.addWall(34, 20);
            walls.addWall(36, 20);


            walls.addWall(22, 24);
            walls.addWall(36, 24);

            walls.addWall(22, 24);
            walls.addWall(36, 24);

            walls.addWall(22, 28);
            walls.addWall(36, 28);

            walls.addWall(22, 32);
            walls.addWall(36, 32);

            walls.addWall(22, 34);
            walls.addWall(36, 34);

            walls.addWall(22, 34);
            walls.addWall(36, 34);

            walls.addWall(22, 38);
            walls.addWall(36, 38);

            walls.addWall(22, 42);
            walls.addWall(36, 42);


            walls.addWall(22, 46);
            walls.addWall(36, 46);


            walls.addWall(22, 48);
            walls.addWall(36, 48);


            walls.addWall(22, 52);
            walls.addWall(24, 52);
            walls.addWall(26, 52);
            walls.addWall(28, 52);
            walls.addWall(32, 52);
            walls.addWall(34, 52);
            walls.addWall(36, 52);



            Labyrinthe labyrinthe = new Labyrinthe(80 , 80, new Hero(10, 10), walls, new Chest(new Position(25, 25)));

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
