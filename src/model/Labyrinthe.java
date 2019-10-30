package model;

import engine.Cmd;
import engine.Game;
import model.enemies.Enemies;
import model.objects.Chest;
import model.walls.Walls;

public class Labyrinthe implements Game {

    private  final int HEIGHT ;
    private  final int WIDTH ;
    private Hero hero;
    private Enemies enemies;
    private Walls walls;
    private Chest chest;
    private boolean isFinished;

    public Labyrinthe(int HEIGHT, int WIDTH, Hero hero, Enemies enemies, Walls walls, Chest chest) {
        this.HEIGHT = HEIGHT;
        this.WIDTH = WIDTH;
        this.hero = hero;
        this.enemies = enemies;
        this.walls = walls;
        this.chest = chest;
        this.isFinished = false;
    }

    @Override
    public void evolve(Cmd userCmd) {
        int x = hero.getX();
        int y = hero.getY();
           switch (userCmd){
               case UP:
                   if (hero.getY()>0 && !enemies.isEnemy(x, y-1) && isFree(x, y-1)){
                       if (chest.isOnChest(x, y-1)){
                           isFinished = true;
                       }
                       hero.goUp();
                   }
                   break;
               case DOWN:
                   if (hero.getY()<HEIGHT && !enemies.isEnemy(x, y+1) && isFree(x, y+1)){
                       if (chest.isOnChest(x, y+1)){
                           isFinished = true;
                       }
                       hero.goDown();
                   }
                   break;
               case LEFT:
                   if (hero.getX()>0 && !enemies.isEnemy(x-1, y) && isFree(x-1, y)){
                       if (chest.isOnChest(x-1, y)){
                           isFinished = true;
                       }
                       hero.goLeft();
                   }
                   break;
               case RIGHT:
                   if (hero.getX()<WIDTH && !enemies.isEnemy(x+1, y) && isFree(x+1, y)){
                       if (chest.isOnChest(x+1, y)){
                           isFinished = true;
                       }
                       hero.goRight();
                   }
                   break;
               case IDLE: break;
               case ATTACK:  this.hero.attaque(); break;
           }
           enemiesProcess();

        System.out.println("("+this.hero.getX()+" , "+this.hero.getY()+" )");
    }

    private void enemiesProcess(){
        enemies.processMonsters(hero.getX(), hero.getY());
    }

    @Override
    public boolean isFinished() {
        return isFinished;
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
}

