package model.enemies;

import model.Position;

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
        boolean isEnem=false;
        for (Enemy enemy : enemies){
            if(enemy.getY()==y &&  enemy.getX()==x)
                isEnem= true;
        }
        return isEnem;
    }

    public void processMonsters(){
        for (Enemy e : enemies){
            e.move();
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
