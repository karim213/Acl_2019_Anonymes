package factories;

import engine.GameEngineGraphical;
import model.*;
import model.enemies.*;
import model.objects.*;
import model.walls.Walls;

public class TestFactory {
    private static GameEngineGraphical engine = null;

    public static GameEngineGraphical createSimpleGame() {

        if (engine == null) {



            Walls walls = new Walls();
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
            objects.addObject(new Trap(new Position(30 , 5)));
            objects.addObject(new Trap(new Position(60 , 5)));
            objects.addObject(new Heal(new Position(120 , 60)));
            objects.addObject(new Heal(new Position(80 , 60)));
            objects.addObject(new Teleporter(new Position(150,23),new Position(90,60)));

            objects.addObject(new Sand(new Position(52, 20)));
            objects.addObject(new Sand(new Position(52, 24)));
            objects.addObject(new Sand(new Position(56, 20)));
            objects.addObject(new Water(new Position(56, 24)));
            objects.addObject(new Sand(new Position(60, 20)));
            objects.addObject(new Water(new Position(60, 24)));
            objects.addObject(new Sand(new Position(64, 20)));
            objects.addObject(new Water(new Position(64, 24)));
            objects.addObject(new Sand(new Position(68, 20)));
            objects.addObject(new Water(new Position(68, 24)));
            objects.addObject(new Sand(new Position(72, 20)));
            objects.addObject(new Water(new Position(72, 24)));
            objects.addObject(new Sand(new Position(76, 20)));
            objects.addObject(new Water(new Position(76, 24)));
            objects.addObject(new Sand(new Position(80, 20)));
            objects.addObject(new Water(new Position(80, 24)));
            objects.addObject(new Sand(new Position(84, 20)));
            objects.addObject(new Water(new Position(84, 24)));
            objects.addObject(new Sand(new Position(88, 20)));
            objects.addObject(new Water(new Position(88, 24)));

            objects.addObject(new Sand(new Position(52, 20)));
            objects.addObject(new Water(new Position(56, 24)));


            Labyrinthe labyrinthe = new Labyrinthe(new Hero(120, 50), walls,objects);

            Enemies enemies = new Enemies();

           Monster m1 = new Monster(28, 44,labyrinthe);
            m1.setMovementStrategy(new SnakeMovement());
            enemies.addEnemie(m1);

            Monster m2 = new Monster(3, 40,labyrinthe);
            m2.setMovementStrategy(new SnakeMovement());
            enemies.addEnemie(m2);

            Monster m3 = new Monster(26, 29,labyrinthe);
            m3.setMovementStrategy(new SnakeMovement());
            enemies.addEnemie(m3);

            Monster m4 = new Monster(30, 30,labyrinthe);
            m4.setMovementStrategy(new SnakeMovement());
            enemies.addEnemie(m4);

            Monster m5 = new Monster(10, 60,labyrinthe);
            m5.setMovementStrategy(new SnakeMovement());
            enemies.addEnemie(m5);

            Monster m6 = new Monster(63, 10,labyrinthe);
            m6.setMovementStrategy(new SnakeMovement());
            enemies.addEnemie(m6);

            Monster m7 = new Monster(26, 12,labyrinthe);
            m7.setMovementStrategy(new SnakeMovement());
            enemies.addEnemie(m7);

            Monster m8 = new Monster(39, 38,labyrinthe);
            m8.setMovementStrategy(new SnakeMovement());
            enemies.addEnemie(m8);

            Ghost g1 = new Ghost(100, 30,labyrinthe);
            g1.setMovementStrategy(new GhostMovement());
            Ghost g2 = new Ghost(0, 30,labyrinthe);
            g2.setMovementStrategy(new GhostMovement());
            Ghost g3 = new Ghost(50, 30,labyrinthe);
            g3.setMovementStrategy(new GhostMovement());

            enemies.addEnemie(g1);
            enemies.addEnemie(g2);
            enemies.addEnemie(g3);

            Boss b1 = new Boss(0, 0,labyrinthe);
            b1.setMovementStrategy(new GhostMovement());

            enemies.addEnemie(b1);


            labyrinthe.setEnemies(enemies);

            PacmanPainter painter = new PacmanPainter(labyrinthe);
            PacmanController controller = new PacmanController(labyrinthe);

            engine = new GameEngineGraphical(labyrinthe, painter, controller);
        }

        return engine;
    }

    public static GameEngineGraphical createLeveledGame() {
        Labyrinthe labyrinthe = new Labyrinthe();
        PacmanPainter painter = new PacmanPainter(labyrinthe);
        PacmanController controller = new PacmanController(labyrinthe);
        engine = new GameEngineGraphical(labyrinthe, painter, controller);
        return engine;
    }
}
