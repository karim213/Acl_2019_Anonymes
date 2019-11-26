package model.enemies;

import model.Labyrinthe;
import model.Position;
import util.AStar;

import java.util.ArrayList;

public class SnakeMovement implements EnemyMovementStrategy {
    private ArrayList<Position> next;
    private int cmpt;
    private int speed;

    public SnakeMovement() {
        this.next = new ArrayList<>();
        cmpt = 0;
        speed = 0;
    }

    @Override
    public void move(Enemy enemy, Labyrinthe game) {

        int distance = (game.getHero().getY() - enemy.getY()) * (game.getHero().getY() - enemy.getY()) + (game.getHero().getX() - (enemy.getX())) * (game.getHero().getX() - (enemy.getX()));

        if (distance < 4000){
            if (cmpt == 0) {
                next = AStar.getPath(game, new Position(game.getHero().getX(), game.getHero().getY()), new Position(enemy.getX(), enemy.getY()));
                if (next != null){
                    cmpt = next.size()-1;
                }
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
}