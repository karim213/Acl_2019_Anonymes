package model.enemies;


import engine.Cmd;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Enemies {
    private List<Enemy> enemies;

    public Enemies() {
        this.enemies = new ArrayList<>();
    }

    public void addEnemie(Enemy enemy){
        enemies.add(enemy);
    }

    public boolean isEnemy(int x , int y){
        Rectangle rect1 = new Rectangle(x*5, y*5, 40, 40);

        boolean isEnem=false;
        for (Enemy enemy : enemies){
            Rectangle rect2 = new Rectangle(enemy.getX()*5 , enemy.getY()*5, 20, 20);
            Rectangle intersection = rect1.intersection(rect2);

            if(intersection.getHeight() > 0 && intersection.getWidth() > 0) {
                isEnem= true;
                }
        }
        return isEnem;
    }

    public void processMonsters(){
        for (Enemy e : enemies){
            e.move();
        }
    }

    public void attack(int x , int y){

        Rectangle rect1 = new Rectangle(x*5 - 20, y*5 - 20, 80, 80);
        Rectangle rect2;

        for (Enemy enemy : enemies){
            if (enemy.getType().equals("Monster")) {
                rect2 = new Rectangle(enemy.getX() *5 , enemy.getY()*5, 20, 20);
            }
            else {
                rect2 = new Rectangle(enemy.getX() *5 , enemy.getY()*5, 40, 40);
            }
            Rectangle intersection = rect1.intersection(rect2);

            if(intersection.getHeight() > 0 && intersection.getWidth() > 0) {
                enemy.receiveDamage();
                if(enemy.getLabyrinthe().getHero().getCurrentCmd() == Cmd.RIGHT) {
                    enemy.goRight();
                    enemy.goRight();
                    enemy.goRight();
                    enemy.goRight();
                }else{
                    enemy.goLeft();
                    enemy.goLeft();
                    enemy.goLeft();
                    enemy.goLeft();
                }
                if (enemy.isDead()) {
                    this.enemies.remove(enemy);
                }
                return;
            }
        }
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void emptyEnemies(){
        enemies.clear();
    }
}
