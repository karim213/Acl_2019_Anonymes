package model;

import engine.Cmd;
import engine.Game;
import model.enemies.Enemies;
import model.objects.Chest;
import model.walls.Walls;

public class Labyrinthe implements Game {

    public static  int HEIGHT ;
    public static  int WIDTH ;
    private Hero hero;
    private Enemies enemies;
    private Walls walls;
    private Chest chest;
    private boolean isFinished;



    public Labyrinthe(int HEIGHT, int WIDTH, Hero hero, Walls walls, Chest chest) {
        this.HEIGHT = HEIGHT;
        this.WIDTH = WIDTH;
        this.hero = hero;
        this.walls = walls;
        this.chest = chest;
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
                       if (chest.isOnChest(x, y-1)){
                           isFinished = true;
                       }
                       hero.goUp();
                   }
                   break;
               case DOWN:
                   if (hero.getY()<HEIGHT && isFree(x, y+1)){
                       if (chest.isOnChest(x, y+1)){
                           isFinished = true;
                       }
                       hero.goDown();
                   }
                   break;
               case LEFT:
                   if (hero.getX()>0 && isFree(x-1, y)){
                       if (chest.isOnChest(x-1, y)){
                           isFinished = true;
                       }
                       hero.goLeft();
                   }
                   break;
               case RIGHT:
                   if (hero.getX()<WIDTH &&  isFree(x+1, y)){
                       if (chest.isOnChest(x+1, y)){
                           isFinished = true;
                       }
                       hero.goRight();
                   }
                   break;
               case IDLE: break;
               case ATTACK:
                   this.hero.attaque();
                   this.attack();
                   break;
           }
            if(enemies.isEnemy(getHero().getX(),getHero().getY()))
            this.getHero().setOver(true);
           enemiesProcess();
    }

    private void enemiesProcess(){
        enemies.processMonsters();
    }

    @Override
    public boolean isFinished() {
        return isFinished||hero.isOver();
    }

    @Override
    public boolean isOver() {
        return hero.isOver();
    }

    public boolean isFree(int x , int y){
        return walls.isPosFree(x, y);
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public Hero getHero() {
        return hero;
    }

    public Chest getChest() {
        return chest;
    }

    public Enemies getEnemies() {
        return enemies;
    }

    public Walls getWalls() {
        return walls;
    }

    public void attack(){
        switch (this.hero.getCurrentCmd()){
            case IDLELEFT:
            case LEFT:
                this.enemies.attack(this.hero.getX() -1 , this.hero.getY());
                break;

            case IDLERIGHT:
            case RIGHT:
                this.enemies.attack(this.hero.getX() +1 , this.hero.getY());
                break;


            case IDLEUP:
            case UP:
                this.enemies.attack(this.hero.getX()  , this.hero.getY() - 1);
                break;


            case IDLEDOWN:
            case DOWN:
                this.enemies.attack(this.hero.getX()  , this.hero.getY() + 1);
                break;

        }
    }
}

