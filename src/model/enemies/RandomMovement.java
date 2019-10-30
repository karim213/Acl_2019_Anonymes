package model.enemies;

import model.Labyrinthe;
import model.Position;

import java.util.Random;

public class RandomMovement implements EnemyMovementStrategy{

    Labyrinthe game;

    public RandomMovement() {
    }

    public void setGame(Labyrinthe game) {
        this.game = game;
    }

    @Override
    public Position move(Position position) {
        Random random = new Random();
        boolean invalide = true;
        Position res = new Position(0, 0);

        while (invalide){
            int direction = random.nextInt(4);

            if (direction == 0){
                if (game.isFree(position.getX()+1, position.getY())){
                    invalide = false;
                    res = new Position(position.getX()+1, position.getY());
                }
            }
            else if (direction == 1){
                if (game.isFree(position.getX()-1, position.getY())){
                    invalide = false;
                    res = new Position(position.getX()-1, position.getY());
                }
            }
            else if (direction == 2) {
                if (game.isFree(position.getX(), position.getY()+1)){
                    invalide = false;
                    res = new Position(position.getX(), position.getY()+1);
                }
            }
            else if (direction == 3) {
                if (game.isFree(position.getX(), position.getY()-1)){
                    invalide = false;
                    res = new Position(position.getX(), position.getY()-1);
                }
            }
        }

        return res;
    }
}
