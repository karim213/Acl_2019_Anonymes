package model;

import engine.Cmd;
import engine.Game;
import model.enemies.Enemies;
import model.objects.Objects;
import model.walls.Walls;

import static util.Constants.*;


public class Labyrinthe implements Game {

    private Hero hero;
    private Enemies enemies;
    private Walls walls;
    private Objects objects;
    private int isFinished;



    public Labyrinthe(Hero hero, Walls walls,Objects objects) {
        this.hero = hero;
        this.walls = walls;
        this.objects = objects;
        this.isFinished = -1;
    }
    public void setEnemies(Enemies enemies) {
        this.enemies = enemies;
    }
    @Override
    public void evolve(Cmd userCmd) {
        int x = hero.getX();
        int y = hero.getY();

           switch (userCmd){
               case UP:
                   if (hero.getY()>0 &&  isFree(x, y-hero.getSpeed())){
                       if (objects.isOnChest(new Position( x, y-hero.getSpeed()))){
                           isFinished = 1;
                       }
                       hero.goUp();
                   }
                   break;
               case DOWN:
                   if (hero.getY()<HEIGHT-10 && isFree(x, y+hero.getSpeed())){
                       if (objects.isOnChest(new Position(x, y+hero.getSpeed()))){
                           isFinished = 1;
                       }
                       hero.goDown();
                   }
                   break;
               case LEFT:
                   if (hero.getX()>0 && isFree(x-hero.getSpeed(), y)){
                       if (objects.isOnChest(new Position(x-hero.getSpeed(), y))){
                           isFinished = 1;
                       }
                       hero.goLeft();
                   }
                   break;
               case RIGHT:
                   if (hero.getX()<WIDTH-10 &&  isFree(x+hero.getSpeed(), y)){
                       if (objects.isOnChest(new Position(x+hero.getSpeed(), y))){
                           isFinished = 1;
                       }
                       hero.goRight();
                   }
                   break;
               case IDLE:
                   hero.stop();
                   break;
               case ATTACK:
                   this.hero.attaque();
                   this.attack();
                   break;
           }
           this.objects.performObjectActions(hero);

        if(enemies.isEnemy(getHero().getX(),getHero().getY())){

            this.getHero().receiveDamage();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
           enemiesProcess();
    }

    private void enemiesProcess(){
        enemies.processMonsters();
    }

    @Override
    public int isFinished() {
        if (hero.isDead())
            isFinished = 1;

        return isFinished;
    }

    @Override
    public void setisFinished(int status) {
        this.isFinished = status;
    }

    @Override
    public boolean isOver() {
        return hero.isDead();
    }

    public boolean isFree(int x , int y){
        return walls.isPosFree(x, y);
    }

    public Hero getHero() {
        return hero;
    }

    public Objects getObjects(){
        return objects;
    }

    public Enemies getEnemies() {
        return enemies;
    }

    public Walls getWalls() {
        return walls;
    }

    public void attack(){
        this.enemies.attack(this.hero.getX(), this.hero.getY());
    }





}

