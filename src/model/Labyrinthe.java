package model;

import engine.Cmd;
import engine.Game;
import model.enemies.Enemy;
import model.enemies.EnemyMovementStrategy;

import java.util.ArrayList;
import java.util.List;

public class Labyrinthe implements Game {

    public static  int HEIGHT ;
    public static  int WIDTH ;
    private Hero hero;
    private List<Enemy> enemies;

    public Labyrinthe(int height, int width) {
        HEIGHT = height;
        WIDTH = width;
        hero = new Hero(0 , 0);
        enemies = new ArrayList<>();
    }

    @Override
    public void evolve(Cmd userCmd) {
           switch (userCmd){
               case UP:
                   if (hero.getY()>0 && isFree( hero.getX(), hero.getY()-1)) {
                       hero.goUp();
                   }
                   break;
               case DOWN:
                   if (hero.getY()<HEIGHT && isFree(hero.getX(), hero.getY()+1)) {
                       hero.goDown();
                   }
                   break;
               case LEFT:
                   if (hero.getX()>0 && isFree(hero.getX()-1, hero.getY())) {
                       hero.goLeft();
                   }
                   break;
               case RIGHT:
                   if (hero.getX()< WIDTH && isFree(hero.getX()+1, hero.getY())) {
                       hero.goRight();
                   }
                   break;
               case IDLE: break;
           }

        System.out.println("("+this.hero.getX()+" , "+this.hero.getY()+" )");
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    public boolean isFree(int x , int y){
        /**
         * Verifier si la position est libre
         */
        return true;
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

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void addEnemy(Enemy enemy){
        enemies.add(enemy);
    }

    public void moveEnemies(){
        for(Enemy enemy : enemies){
                enemy.move();
        }
    }

    public void setStrategy(EnemyMovementStrategy strategy){
        for(Enemy enemy : enemies){
            enemy.setMovementStrategy(strategy);
        }
    }
}
