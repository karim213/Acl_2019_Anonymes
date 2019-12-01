package model.enemies;

import model.Labyrinthe;
import model.MovableEntity;


public abstract class Enemy extends MovableEntity {
    private EnemyMovementStrategy movementStrategy;
    private Labyrinthe labyrinthe;

    protected Enemy(int x, int y, int pv , Labyrinthe labyrinthe) {
        super(x, y, pv, 1);
        this.labyrinthe = labyrinthe;
    }

    public abstract String getType();

    public void move(){
        movementStrategy.move(this, labyrinthe);
    }

    public void setMovementStrategy(EnemyMovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }
}
