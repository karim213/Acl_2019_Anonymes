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
    private boolean isFinished;



    public Labyrinthe(Hero hero, Walls walls,Objects objects) {
        this.hero = hero;
        this.walls = walls;
        this.objects = objects;
        this.isFinished = false;
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
                   if (hero.getY()>0 &&  isFree(x, y-1)){
                       if (objects.isOnChest(new Position( x, y-1))){
                           isFinished = true;
                       }
                       hero.goUp();
                   }
                   break;
               case DOWN:
                   if (hero.getY()<HEIGHT-10 && isFree(x, y+1)){
                       if (objects.isOnChest(new Position(x, y+1))){
                           isFinished = true;
                       }
                       hero.goDown();
                   }
                   break;
               case LEFT:
                   if (hero.getX()>0 && isFree(x-1, y)){
                       if (objects.isOnChest(new Position(x-1, y))){
                           isFinished = true;
                       }
                       hero.goLeft();
                   }
                   break;
               case RIGHT:
                   if (hero.getX()<WIDTH-10 &&  isFree(x+1, y)){
                       if (objects.isOnChest(new Position(x+1, y))){
                           isFinished = true;
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

            this.getHero().subPv();
            if (getHero().getPv() == 0)
                this.getHero().setOver(true);
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
    public boolean isFinished() {
        return isFinished || hero.isOver();
    }

    @Override
    public boolean isOver() {
        return hero.isOver();
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

