package factories;

import engine.GameEngineGraphical;
import model.*;
import model.enemies.*;
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
            objects.addHeal(new Position(120 , 60));
            objects.addHeal(new Position(80 , 60));

            Labyrinthe labyrinthe = new Labyrinthe(new Hero(120, 50), walls,objects);

            Enemies enemies = new Enemies();

            Monster m1 = new Monster(28, 44,labyrinthe);
            enemies.addEnemie(m1);

            Monster m2 = new Monster(3, 40,labyrinthe);
            enemies.addEnemie(m2);

            Monster m3 = new Monster(26, 29,labyrinthe);
            enemies.addEnemie(m3);

            Monster m4 = new Monster(30, 30,labyrinthe);
            enemies.addEnemie(m4);

            Monster m5 = new Monster(10, 60,labyrinthe);
            enemies.addEnemie(m5);

            Monster m6 = new Monster(63, 10,labyrinthe);
            enemies.addEnemie(m6);

            Monster m7 = new Monster(26, 12,labyrinthe);
            enemies.addEnemie(m7);

            Monster m8 = new Monster(39, 38,labyrinthe);
            enemies.addEnemie(m8);

            Ghost g1 = new Ghost(100, 30,labyrinthe);
//            Ghost g2 = new Ghost(3, 43,labyrinthe);
//            Ghost g3 = new Ghost(42, 41,labyrinthe);
//            Ghost g4 = new Ghost(60, 60,labyrinthe);

            enemies.addEnemie(g1);
//            enemies.addEnemie(g2);
//            enemies.addEnemie(g3);
//            enemies.addEnemie(g4);

            enemies.setStrategy(new RandomMovement());
            g1.setMovementStrategy(new SmartMovement());
            labyrinthe.setEnemies(enemies);

            PacmanPainter painter = new PacmanPainter(labyrinthe);
            PacmanController controller = new PacmanController(labyrinthe);

            engine = new GameEngineGraphical(labyrinthe, painter, controller);
        }

        return engine;
    }
}
