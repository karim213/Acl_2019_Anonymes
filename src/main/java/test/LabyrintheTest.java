package test;

import engine.Cmd;
import model.Labyrinthe;
import model.Position;
import model.objects.Heal;
import model.objects.Sand;
import model.objects.Trap;
import model.walls.Wall;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LabyrintheTest {

    private Labyrinthe labyrinthe;



    //test moving hero

    @Test
    public void depHero(){
        labyrinthe = new Labyrinthe();
        labyrinthe.getHero().setX(5);
        labyrinthe.getHero().setY(5);
        labyrinthe.getHero().goRight();
        labyrinthe.getHero().goDown();
        labyrinthe.getHero().goLeft();
        labyrinthe.getHero().goUp();
        labyrinthe.getHero().goLeft();
        assertEquals(3  , labyrinthe.getHero().getX());
        assertEquals(5  , labyrinthe.getHero().getY());

    }


    //test if walls are added
    @Test
    public void addWall(){
        labyrinthe = new Labyrinthe();
        List<Wall> walls = new ArrayList<>();
        walls.add(new Wall(5 , 6));
        walls.add(new Wall(10 , 20));
        labyrinthe.setWalls(walls);
        assertEquals(false, labyrinthe.isFree(5 , 6));
        assertEquals(false, labyrinthe.isFree(10 , 20));
        assertEquals(true, labyrinthe.isFree(20 , 6));
    }


    //test if wall block hero
    @Test
    public void DepHeroWall(){
        labyrinthe = new Labyrinthe();
        List<Wall> walls = new ArrayList<>();
        walls.add(new Wall(5 , 6));
        labyrinthe.setWalls(walls);
        labyrinthe.getHero().setX(4);
        labyrinthe.getHero().setY(6);
        labyrinthe.evolve(Cmd.RIGHT);
        assertEquals(4  , labyrinthe.getHero().getX());
        assertEquals(6  , labyrinthe.getHero().getY());

    }


    //test heal action
    @Test
    public void healAction(){
        labyrinthe = new Labyrinthe();
        int pv = labyrinthe.getHero().getPv();
        List<Heal> heals = new ArrayList<>();
        heals.add(new Heal(new Position(6 , 6)));
        labyrinthe.setHeals(heals);
        labyrinthe.getHero().setX(4);
        labyrinthe.getHero().setY(6);
        labyrinthe.evolve(Cmd.RIGHT);
        assertEquals(pv+1  , labyrinthe.getHero().getPv());

    }


    //test trap action
    @Test
    public void trapAction(){
        labyrinthe = new Labyrinthe();
        List<Trap> traps = new ArrayList<>();
        traps.add(new Trap(new Position(6 , 6)));
        labyrinthe.setTraps(traps);
        labyrinthe.getHero().setX(4);
        labyrinthe.getHero().setY(6);
        labyrinthe.evolve(Cmd.RIGHT);
        assertEquals(true , labyrinthe.getHero().isDead());

    }


    //Sand speed action
    @Test
    public void sandAction(){
        labyrinthe = new Labyrinthe();
        int initialSpeed = labyrinthe.getHero().getSpeed();
        List<Sand> sands = new ArrayList<>();
        sands.add(new Sand(new Position(5 , 6)));
        labyrinthe.setSands(sands);
        labyrinthe.getHero().setX(3);
        labyrinthe.getHero().setY(6);
        labyrinthe.evolve(Cmd.RIGHT);
        assertEquals(false , initialSpeed == labyrinthe.getHero().getSpeed() );
    }


    //set speed hero to 1
    @Test
    public void setSpeedOne(){
        labyrinthe = new Labyrinthe();
        int posX = labyrinthe.getHero().getX();
        labyrinthe.getHero().setSpeed(1);
        labyrinthe.evolve(Cmd.RIGHT);
        assertEquals(posX+1 , labyrinthe.getHero().getX());

    }

    //set speed hero to 0
    @Test
    public void setSpeedZero(){
        labyrinthe = new Labyrinthe();
        int posX = labyrinthe.getHero().getX();
        labyrinthe.getHero().setSpeed(0);
        labyrinthe.evolve(Cmd.RIGHT);
        assertEquals(posX , labyrinthe.getHero().getX());

    }



}