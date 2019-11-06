package model.enemies;


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

        Rectangle rect1 = new Rectangle(x*5 - 60, y*5 - 60, 160, 160);

        for (Enemy enemy : enemies){
            Rectangle rect2 = new Rectangle(enemy.getX() *5 , enemy.getY()*5, 20, 20);
            Rectangle intersection = rect1.intersection(rect2);

            if(intersection.getHeight() > 0 && intersection.getWidth() > 0) {
                this.enemies.remove(enemy);
                return;
            }
        }
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
