package model.enemies;

import engine.Cmd;

public class RandomMovement implements EnemyMovementStrategy {

    @Override
    public void move(Enemy enemy) {
        if(enemy.getCurrentCmd()==null) {
            int n = (int) (Math.random() * 4) + 1;
            switch (n) {
                case 1:
                    enemy.goUp();
                    break;
                case 2:
                    enemy.goDown();
                    break;
                case 3:
                    enemy.goLeft();
                    break;
                case 4:
                    enemy.goRight();
                    break;
            }
        }
        else if(enemy.getCurrentCmd()== Cmd.IDLEUP) {
            enemy.goUp();
        }
        else if(enemy.getCurrentCmd()== Cmd.IDLEDOWN) {
            enemy.goDown();
        }
        else if(enemy.getCurrentCmd()== Cmd.IDLELEFT) {
            enemy.goLeft();
        }
        else if(enemy.getCurrentCmd()== Cmd.IDLERIGHT) {
            enemy.goRight();
        }
    }
}
