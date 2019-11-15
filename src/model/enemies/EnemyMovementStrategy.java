package model.enemies;

import model.Labyrinthe;

public interface EnemyMovementStrategy {
    void move(Enemy enemy, Labyrinthe game);
}
