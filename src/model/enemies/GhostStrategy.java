package model.enemies;

import model.Labyrinthe;
import model.Position;
import model.walls.Walls;
import util.AStar;

import java.util.ArrayList;

public class GhostStrategy implements EnemyMovementStrategy {
    private ArrayList<Position> next;
    private int cmpt;
    private int speed;

    public GhostStrategy() {
        this.next = new ArrayList<>();
        cmpt = 0;
        speed = 0;
    }

    @Override
    public void move(Enemy enemy, Labyrinthe game) {

        if (cmpt == 0) {
            next = AStar.getPath(new Labyrinthe(null, new Walls(), null), new Position(game.getHero().getX(), game.getHero().getY()), new Position(enemy.getX(), enemy.getY()));
            cmpt = next.size()-1;
        }
        else {
            if (next.get(cmpt) != null){
                enemy.setY(next.get(cmpt).getY());
                enemy.setX(next.get(cmpt).getX());
            }
            if (speed == 0) {
                cmpt--;
                speed = 0;
            }
            else {
                speed--;
            }
        }
    }
}
