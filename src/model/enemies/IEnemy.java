package model.enemies;

import model.Position;

public abstract class IEnemy {
    protected Enemies enemies;
    protected EnemyMovementStrategy strategy;

    public IEnemy(Enemies enemies, EnemyMovementStrategy strategy) {
        this.enemies = enemies;
        this.strategy = strategy;
    }

    abstract void goTo(int x, int y);
    abstract Position getPosition();
}
