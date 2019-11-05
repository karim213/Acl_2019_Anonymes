package model.enemies;

import model.Position;

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
        Rectangle rect1 = new Rectangle(x*10, y*5, 20, 40);

        boolean isEnem=false;
        for (Enemy enemy : enemies){
            Rectangle rect2 = new Rectangle(enemy.getX() *10 , enemy.getY()*5, 20, 10);
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

        Rectangle rect1 = new Rectangle(x*10, y*5, 20, 40);

        for (Enemy enemy : enemies){
            Rectangle rect2 = new Rectangle(enemy.getX() *10 , enemy.getY()*5, 20, 10);
            Rectangle intersection = rect1.intersection(rect2);

            if(intersection.getHeight() > 0 && intersection.getWidth() > 0) {
                this.enemies.remove(enemy);
                return;
            }
        }
    }

    public List<Position> getEnemiesPosition(){
        List<Position> res = new ArrayList<>();

        for (int i = 0; i < enemies.size(); i++){
            Position position = new Position(enemies.get(i).getX(),enemies.get(i).getY());
            res.add(position);
        }
        return res;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void setStrategy(EnemyMovementStrategy strategy){
        for(Enemy enemy : enemies){
            enemy.setMovementStrategy(strategy);
        }
    }
}
