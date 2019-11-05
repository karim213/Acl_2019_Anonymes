package model.enemies;


public class RandomMovement implements EnemyMovementStrategy {

    private int nbMvts = 0;

    @Override
    public void move(Enemy enemy) {
        if (nbMvts == 0){
                int n = (int) (Math.random() * 4);
                switch (n) {
                    case 0:
                        enemy.goUp();
                        break;
                    case 1:
                        enemy.goDown();
                        break;
                    case 2:
                        enemy.goLeft();
                        break;
                    case 3:
                        enemy.goRight();
                        break;
                }
            nbMvts = 10;
        }
        else {
            switch (enemy.getCurrentCmd()) {
                case UP:
                    enemy.goUp();
                    break;
                case DOWN:
                    enemy.goDown();
                    break;
                case LEFT:
                    enemy.goLeft();
                    break;
                case RIGHT:
                    enemy.goRight();
                    break;
            }
        }
        nbMvts--;
    }
}
