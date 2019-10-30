package model.enemies;

import model.Position;

import java.util.ArrayList;
import java.util.List;

public class Enemies {
    private List<IEnemy> enemies;

    public Enemies() {
        this.enemies = new ArrayList<>();
    }

    public void addEnemie(IEnemy enemy){
        enemies.add(enemy);
    }

    public boolean isEnemy(int x, int y){
        for (IEnemy e : enemies){
            if (e.getPosition().equals(new Position(x, y))){
                return true;
            }
        }
        return false;
    }

    public void processMonsters(int x, int y){
        for (IEnemy e : enemies){
            e.goTo(x, y);
        }
    }

    public List<Position> getEnemiesPosition(){
        List<Position> res = new ArrayList<>();

        for (int i = 0; i < enemies.size(); i++){
                    res.add(enemies.get(i).getPosition());
        }
        return res;
    }

}
