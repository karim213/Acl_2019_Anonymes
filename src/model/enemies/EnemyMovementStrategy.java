package model.enemies;

import model.Position;

public interface EnemyMovementStrategy {
    Position move(Position position);
}
