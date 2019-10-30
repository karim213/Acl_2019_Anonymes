package model.enemies;

import model.Position;

public class Monster extends IEnemy{

    private Position position;

    public Monster(int x, int y, Enemies enemies, EnemyMovementStrategy strategy) {
       super(enemies, strategy);
       this.position = new Position(x, y);
    }

    @Override
    void goTo(int x, int y) {
        this.position = strategy.move(this.position);
    }

    @Override
    Position getPosition() {
        return position;
    }
}
